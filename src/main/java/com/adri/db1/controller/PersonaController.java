package com.adri.db1.controller;

import com.adri.db1.IPersonaService;
import com.adri.db1.model.PersonaInputDTO;
import com.adri.db1.model.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/persona")
@RestController
public class PersonaController {
    @Autowired
    IPersonaService personaService;

    @PostMapping("/addPersona")
    public PersonaOutputDTO addPersona(@RequestBody PersonaInputDTO personaIn) throws Exception{
        return personaService.addPersona(personaIn);
    }

    @GetMapping("/{id}")
    public PersonaOutputDTO getPersonaById(@PathVariable("id") Integer id) throws Exception{
        return personaService.getPersonaById(id);
    }
    @GetMapping("/nombre/{nombre}")
    public PersonaOutputDTO getPersonaByName(@PathVariable("nombre") String nombre) throws Exception{
        return personaService.getPersonaByName(nombre);
    }
    @GetMapping("/all")
    public List<PersonaOutputDTO> getPersonas() throws Exception{
        return personaService.getPersonas();
    }
}
