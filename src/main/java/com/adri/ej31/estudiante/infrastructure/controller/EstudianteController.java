package com.adri.ej31.estudiante.infrastructure.controller;

import com.adri.ej31.estudiante.application.port.EstudianteService;
import com.adri.ej31.estudiante.infrastructure.dto.input.EstudianteInputDTO;
import com.adri.ej31.estudiante.infrastructure.dto.output.EstudianteFullOutputDTO;
import com.adri.ej31.estudiante.infrastructure.dto.output.EstudianteOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

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
            return new EstudianteFullOutputDTO(service.findEstudianteById(id));
        }
        else {
            return new EstudianteOutputDTO(service.findEstudianteById(id));
        }
    }

    @PutMapping("/{id}")
    public EstudianteOutputDTO updateEstudiante(
            @PathVariable("id") String id,
            @Valid @RequestBody EstudianteInputDTO estudianteInputDTO
    ){
        return  service.update(id, estudianteInputDTO);
    }

    @PostMapping("/add")
    public EstudianteOutputDTO addEstudiante(@Valid @RequestBody EstudianteInputDTO estudiante){
        return service.save(estudiante);
    }

    @DeleteMapping("/{id}")
    public void deleteEstudiante(@PathVariable("id")String id){
        service.deleteById(id);
    }
}
