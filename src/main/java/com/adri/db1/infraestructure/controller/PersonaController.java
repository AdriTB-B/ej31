package com.adri.db1.infraestructure.controller;

import com.adri.db1.application.port.*;
import com.adri.db1.infraestructure.dto.input.PersonaInputDTO;
import com.adri.db1.infraestructure.dto.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping("/addPersona")
    public PersonaOutputDTO addPersona(@Valid @RequestBody PersonaInputDTO personaIn) throws Exception{
        return createPersona.addPersona(personaIn);
    }

    @GetMapping("/{id}")
    public PersonaOutputDTO getPersonaById(@PathVariable("id") Integer id) throws Exception{
        return readPersona.getPersonaById(id);
    }
    @GetMapping("/nombre/{nombre}")
    public List<PersonaOutputDTO> getPersonaByName(@PathVariable("nombre") String nombre) throws Exception{
        return readPersona.getPersonaByName(nombre);
    }
    @GetMapping("/all")
    public List<PersonaOutputDTO> getPersonas() throws Exception{
        return readPersona.getPersonas();
    }

    @PutMapping("/{id}")
    public PersonaOutputDTO updatePersonaById(@RequestBody PersonaInputDTO personaIn, @PathVariable("id")Integer id) throws Exception{
        return updatePersona.updatePersona(id, personaIn);
    }

    @DeleteMapping("/{id}")
    public void deletePersonaById(@PathVariable("id") Integer id) throws Exception {
        deletePersona.deletePersona(id);
    }
}
