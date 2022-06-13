package com.adri.ej31.persona.infraestructure.controller;

import com.adri.ej31.exception.IncorrectRolException;
import com.adri.ej31.persona.application.port.CreatePersonaPort;
import com.adri.ej31.persona.application.port.DeletePersonaPort;
import com.adri.ej31.persona.application.port.ReadPersonaPort;
import com.adri.ej31.persona.application.port.UpdatePersonaPort;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infraestructure.dto.input.PersonaInputDTO;
import com.adri.ej31.persona.infraestructure.dto.output.PersonaEstudianteOutputDTO;
import com.adri.ej31.persona.infraestructure.dto.output.PersonaOutputDTO;
import com.adri.ej31.persona.infraestructure.dto.output.PersonaProfesorOuputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        switch (rol) {
            case "persona": return new PersonaOutputDTO(persona);
            case "estudiante": {
                if(persona.getRolEstudiante() != null) return new PersonaEstudianteOutputDTO(persona);
                else return new PersonaOutputDTO(persona);
            }
            case "profesor": {
                if(persona.getRolProfesor() != null) return new PersonaProfesorOuputDTO(persona);
                else return new PersonaOutputDTO(persona);
            }
            default: throw new IncorrectRolException(rol + " no es un rol válido. Opciones: persona/ estudiante/ profesor");
        }
    }
}
