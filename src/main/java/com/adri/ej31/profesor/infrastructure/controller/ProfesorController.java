package com.adri.ej31.profesor.infrastructure.controller;

import com.adri.ej31.profesor.domain.ProfesorEntity;
import com.adri.ej31.profesor.application.port.ProfesorService;
import com.adri.ej31.profesor.infrastructure.dto.input.ProfesorInputDTO;
import com.adri.ej31.profesor.infrastructure.dto.output.ProfesorOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    @Autowired
    ProfesorService service;

    @GetMapping("/{id}")
    public ProfesorEntity getProfesorById(@PathVariable("id")String id){
        return service.findProfesorById(id);
    }

    @PostMapping("/add")
    public ProfesorOutputDTO addProfesor(@Valid @RequestBody ProfesorInputDTO profesor){
        return service.save(profesor);
    }

    @PutMapping("/{id}")
    public ProfesorOutputDTO updateProfesor(@PathVariable("id")String id, @Valid @RequestBody ProfesorInputDTO profesor){
        return service.updateProfesor(id, profesor);
    }

    @DeleteMapping("/{id}")
    public void deleteProfesor(@PathVariable("id")String id){
        service.deleteProfesor(id);
    }

}
