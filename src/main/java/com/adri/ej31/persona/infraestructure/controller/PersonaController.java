package com.adri.ej31.persona.infraestructure.controller;

import com.adri.ej31.estudiante.application.port.EstudianteService;
import com.adri.ej31.estudiante.infrastructure.dto.output.EstudianteFullOutputDTO;
import com.adri.ej31.estudiante.infrastructure.repository.EstudianteRepository;
import com.adri.ej31.exception.IncorrectRolException;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infraestructure.dto.input.PersonaInputDTO;
import com.adri.ej31.persona.infraestructure.dto.output.PersonaEstudianteOutputDTO;
import com.adri.ej31.persona.infraestructure.dto.output.PersonaOutputDTO;
import com.adri.ej31.persona.application.port.CreatePersonaPort;
import com.adri.ej31.persona.application.port.DeletePersonaPort;
import com.adri.ej31.persona.application.port.ReadPersonaPort;
import com.adri.ej31.persona.application.port.UpdatePersonaPort;
import com.adri.ej31.persona.infraestructure.dto.output.PersonaProfesorOuputDTO;
import com.adri.ej31.profesor.application.port.ProfesorService;
import com.adri.ej31.profesor.infrastructure.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/persona")
@RestController
public class PersonaController {
    @Autowired
    CreatePersonaPort createPersona;
    @Autowired
    ReadPersonaPort readPersona;
    @Autowired
    UpdatePersonaPort updatePersona;
    @Autowired
    DeletePersonaPort deletePersona;
    @Autowired
    ProfesorService profesorService;
    @Autowired
    EstudianteService estudianteService;

    @PostMapping("/add")
    public PersonaOutputDTO addPersona(@Valid @RequestBody PersonaInputDTO personaIn) {
        return createPersona.addPersona(personaIn);
    }

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
    @GetMapping("/all")
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
        return switch (rol) {
            case "persona" -> new PersonaOutputDTO(persona);
            case "estudiante" -> new PersonaEstudianteOutputDTO(persona);
            case "profesor" -> new PersonaProfesorOuputDTO(persona);
            default -> throw new IncorrectRolException(rol + " no es un rol válido. Opciones: persona/ estudiante/ profesor");
        };
    }
}
