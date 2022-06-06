package com.adri.ej31.estudiante.infrastructure.controller;

import com.adri.ej31.estudiante.domain.EstudianteEntity;
import com.adri.ej31.estudiante.application.port.EstudianteService;
import com.adri.ej31.estudiante.infrastructure.dto.input.EstudianteInputDTO;
import com.adri.ej31.estudiante.infrastructure.dto.output.EstudianteFullOutputDTO;
import com.adri.ej31.estudiante.infrastructure.dto.output.EstudianteOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    @Autowired
    EstudianteService service;
    @GetMapping("/{id}")
    public EstudianteOutputDTO getEstudianteById(
            @PathVariable(name = "id") String id,
            @RequestParam(name = "outputType", defaultValue = "simple") String outputType)
    {
        if(outputType.equals("full")) {
            return new EstudianteFullOutputDTO(service.findById(id));
        }
        else {
            return new EstudianteOutputDTO(service.findById(id));
        }
    }

    @PostMapping("/add")
    public EstudianteOutputDTO addEstudiante(@RequestBody @Valid EstudianteInputDTO estudiante){
        return service.save(estudiante);
    }

    @DeleteMapping("/{id}")
    public void deleteEstudiante(@PathVariable("id")String id){
        service.deleteById(id);
    }
}
