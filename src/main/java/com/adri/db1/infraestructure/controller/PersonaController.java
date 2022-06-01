package com.adri.db1.infraestructure.controller;

import com.adri.db1.application.port.*;
import com.adri.db1.infraestructure.dto.input.PersonaInputDTO;
import com.adri.db1.infraestructure.dto.output.PersonaOutputDTO;
import com.adri.db1.infraestructure.exception.CustomError;
import com.adri.db1.infraestructure.exception.NotFoundException;
import com.adri.db1.infraestructure.exception.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Date;
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
    public PersonaOutputDTO addPersona(@Valid @RequestBody PersonaInputDTO personaIn) {
        return createPersona.addPersona(personaIn);
    }

    @GetMapping("/{id}")
    public PersonaOutputDTO getPersonaById(@PathVariable("id") Integer id) {
        return readPersona.getPersonaById(id);
    }
    @GetMapping("/nombre/{nombre}")
    public List<PersonaOutputDTO> getPersonaByName(@PathVariable("nombre") String nombre) throws Exception{
        return readPersona.getPersonaByName(nombre);
    }
    @GetMapping("/all")
    public List<PersonaOutputDTO> getPersonas() {
        return readPersona.getPersonas();
    }

    @PutMapping("/{id}")
    public PersonaOutputDTO updatePersonaById(
            @Valid @RequestBody PersonaInputDTO personaIn,
            @PathVariable("id")Integer id
    ) {
        return updatePersona.updatePersona(id, personaIn);
    }

    @DeleteMapping("/{id}")
    public void deletePersonaById(@PathVariable("id") Integer id) {
        deletePersona.deletePersona(id);
    }
}
