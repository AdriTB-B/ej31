package com.adri.db1.infraestructure.controller;

import com.adri.db1.application.port.IPersonaService;
import com.adri.db1.infraestructure.dto.input.PersonaInputDTO;
import com.adri.db1.infraestructure.dto.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<PersonaOutputDTO> getPersonaByName(@PathVariable("nombre") String nombre) throws Exception{
        return personaService.getPersonaByName(nombre);
    }
    @GetMapping("/all")
    public List<PersonaOutputDTO> getPersonas() throws Exception{
        return personaService.getPersonas();
    }

    @PutMapping("/{id}")
    public PersonaOutputDTO updatePersonaById(@RequestBody PersonaInputDTO personaIn, @PathVariable("id")Integer id) throws Exception{
        return personaService.updatePersona(id, personaIn);
    }

    @DeleteMapping("/{id}")
    public void deletePersonaById(@PathVariable("id") Integer id) throws Exception {
        personaService.deletePersona(id);
    }
}
