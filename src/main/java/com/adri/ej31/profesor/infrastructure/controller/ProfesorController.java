package com.adri.ej31.profesor.infrastructure.controller;

import com.adri.ej31.profesor.domain.ProfesorEntity;
import com.adri.ej31.profesor.application.port.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    @Autowired
    ProfesorService service;

    @GetMapping("/{id}")
    public ProfesorEntity getProfesorById(@PathVariable("id")String id){
        return service.findById(id);
    }

    @PostMapping("/add")
    public ProfesorEntity addProfesor(@RequestBody ProfesorEntity profesor){
        return service.save(profesor);
    }

}
