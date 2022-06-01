package com.adri.db1.infraestructure.controller;

import com.adri.db1.application.port.*;
import com.adri.db1.infraestructure.dto.input.PersonaInputDTO;
import com.adri.db1.infraestructure.dto.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PersonaOutputDTO> getPersonaById(@PathVariable("id") Integer id) {
        try{
            return new ResponseEntity<>(readPersona.getPersonaById(id), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
    public ResponseEntity<PersonaOutputDTO> updatePersonaById(
            @Valid @RequestBody PersonaInputDTO personaIn,
            @PathVariable("id")Integer id
    ) {
        try{
            return new ResponseEntity<>(updatePersona.updatePersona(id, personaIn), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonaById(@PathVariable("id") Integer id) {
        try{
            deletePersona.deletePersona(id);
            return new ResponseEntity<>(
                    "Se ha eliminado la persona con id " + id + " correctamente",
                    HttpStatus.OK
            );
        }catch(Exception e){
            return new ResponseEntity<>(
                    "No se ha encontrado ninguna persona con id " + id + " que eliminar",
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
