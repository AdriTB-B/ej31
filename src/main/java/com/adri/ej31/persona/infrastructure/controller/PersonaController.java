package com.adri.ej31.persona.infrastructure.controller;

import com.adri.ej31.exception.IncorrectRolException;
import com.adri.ej31.feign.IFeignServer;
import com.adri.ej31.persona.application.port.CreatePersonaPort;
import com.adri.ej31.persona.application.port.DeletePersonaPort;
import com.adri.ej31.persona.application.port.ReadPersonaPort;
import com.adri.ej31.persona.application.port.UpdatePersonaPort;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infrastructure.dto.input.PersonaInputDTO;
import com.adri.ej31.persona.infrastructure.dto.output.PersonaEstudianteOutputDTO;
import com.adri.ej31.persona.infrastructure.dto.output.PersonaOutputDTO;
import com.adri.ej31.persona.infrastructure.dto.output.PersonaProfesorOuputDTO;
import com.adri.ej31.profesor.infrastructure.dto.output.ProfesorOutputDTO;
import org.hibernate.type.descriptor.java.DateTypeDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/persona")
@RestController
public class PersonaController {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    CreatePersonaPort createPersona;
    @Autowired
    ReadPersonaPort readPersona;
    @Autowired
    UpdatePersonaPort updatePersona;
    @Autowired
    DeletePersonaPort deletePersona;
    @Autowired
    IFeignServer feignServer;

    @CrossOrigin(origins="https://cdpn.io")
    @PostMapping
    public PersonaOutputDTO addPersona(@Valid @RequestBody PersonaInputDTO personaIn) {
        return createPersona.addPersona(personaIn);
    }

    //Ejercicio RestTemplate y Feign////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/profesor/{id}")
    public ProfesorOutputDTO getProfesorRestTemplate(@PathVariable("id")String id){
        //RestTemplate
//        ResponseEntity<ProfesorOutputDTO> profesor = new RestTemplate().getForEntity(
//                "http://localhost:8081/profesor/" + id,
//                ProfesorOutputDTO.class
//        );
//        if (profesor.getStatusCode()== HttpStatus.OK)
//            return profesor.getBody();
//        throw new RuntimeException("The server didn't respond OK");
        //Feign
        return feignServer.getProfesorByFeign(id);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Ejercicio DBA1. Criteria//////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/filter")
    public Page<PersonaOutputDTO> getFilteredPersonas(
            @RequestParam Map<String,String> allParams,
            @RequestParam("page") int pageNum,
            @PageableDefault Pageable pageable
    ) {
        // Si no hay parámetros devuelve todas las personas sin filtrar
        if (checkParams(allParams)) {
            return readPersona.getPersonasPageable(pageable).map(PersonaOutputDTO::new);
        }

        CriteriaBuilder critBuilder = entityManager.getCriteriaBuilder();

        // Personas
        CriteriaQuery<PersonaEntity> queryPersona = critBuilder.createQuery(PersonaEntity.class);
        Root<PersonaEntity> rootPersona = queryPersona.from(PersonaEntity.class);
        // Seteo de condiciones
        List<String> fieldNames = List.of("usuario", "name", "surname", "created_date");
        List<Predicate> predicates = new ArrayList<>();
        allParams.forEach((field, value)->{
            if(fieldNames.contains(field)) {
                // Campo fecha de creación
                if(field.equals("created_date")){
                    Date creationDate = parseDate(value);
                    if(allParams.get("after_before") == null){
                        // Igual a la fecha de creación
                        predicates.add(critBuilder.equal(rootPersona.get(field), creationDate));
                    }else {
                        if(allParams.get("after_before").equals("after")){
                            // Posterior a la fecha de creación
                            predicates.add(critBuilder.greaterThan(rootPersona.get(field), creationDate));
                        }else if(allParams.get("after_before").equals("before")) {
                            // Anterior a la fecha de creación
                            predicates.add(critBuilder.lessThan(rootPersona.get(field), creationDate));
                        }
                    }
                }
                // Demás campos
                else predicates.add(critBuilder.equal(rootPersona.get(field), value));
            }
        });
        queryPersona.select(rootPersona).where(predicates.toArray(new Predicate[predicates.size()]));
        // Personas
        List<PersonaOutputDTO> listaPersonas = entityManager
                .createQuery(queryPersona)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultStream()
                .map(PersonaOutputDTO::new)
                .collect(Collectors.toList()
                );

        // Count personas
        CriteriaQuery<Long> queryCount = critBuilder.createQuery(Long.class);
        Root<PersonaEntity> rootCount = queryCount.from(PersonaEntity.class);
        queryCount.select(critBuilder.count(rootCount)).where(critBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        Long count = entityManager.createQuery(queryCount).getSingleResult();

        return new PageImpl<PersonaOutputDTO>(
                listaPersonas,
                pageable,
                count
        );
    }

    private Date parseDate(String value) {
        Date creationDate;
        try{
            creationDate = new SimpleDateFormat("yyy-MM-dd").parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return creationDate;
    }

    private boolean checkParams(Map<String, String> allParams) {
        allParams.remove("size");
        allParams.remove("page");
        return allParams.isEmpty();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/{id}")
    public PersonaOutputDTO getPersonaById(
            @PathVariable("id") String id,
            @RequestParam(name = "rol", defaultValue = "persona")String rol
    ) {
        PersonaOutputDTO persona = getByRol(rol, readPersona.getPersonaById(id));
        return persona;
    }
    @GetMapping("/nombre/{nombre}")
    public List<PersonaOutputDTO> getPersonaByName(
            @PathVariable("nombre") String nombre,
            @RequestParam(name = "rol", defaultValue = "persona")String rol
    ) throws Exception{
        List<PersonaOutputDTO> personas = readPersona.getPersonaByName(nombre).stream()
                .map(per -> getByRol(rol, per))
                .collect(Collectors.toList()
                );
        return personas;
    }
    @CrossOrigin(origins="https://cdpn.io")
    @GetMapping
    public List<PersonaOutputDTO> getPersonas(@RequestParam(name = "rol", defaultValue = "persona")String rol) {
        List<PersonaOutputDTO> personas = readPersona.getPersonas().stream()
                .map(per -> getByRol(rol, per))
                .collect(Collectors.toList()
                );
        return personas;
    }

    @PutMapping("/{id}")
    public PersonaOutputDTO updatePersonaById(
            @RequestBody PersonaInputDTO personaIn,
            @PathVariable("id")String id
    ) {
        return updatePersona.updatePersona(id, personaIn);
    }

    @DeleteMapping("/{id}")
    public void deletePersonaById(@PathVariable("id") String id) {
        deletePersona.deletePersona(id);
    }

    private PersonaOutputDTO getByRol(String rol, PersonaEntity persona){
        switch (rol) {
            case "persona": return new PersonaOutputDTO(persona);
            case "estudiante": {
                if(persona.getRolEstudiante() != null) return new PersonaEstudianteOutputDTO(persona);
                else return new PersonaOutputDTO(persona);
            }
            case "prof": {
                if(persona.getRolProfesor() != null) return new PersonaProfesorOuputDTO(persona);
                else return new PersonaOutputDTO(persona);
            }
            default: throw new IncorrectRolException(rol + " no es un rol válido. Opciones: persona/ estudiante/ profesor");
        }
    }

    //Prueba dev tools////////////////////
    @GetMapping("/saludo")
    public String saludo(){
        return "Hola amigo";
    }
}