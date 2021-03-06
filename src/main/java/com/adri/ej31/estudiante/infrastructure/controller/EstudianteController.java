package com.adri.ej31.estudiante.infrastructure.controller;

import com.adri.ej31.estudiante.application.port.EstudianteService;
import com.adri.ej31.estudiante.infrastructure.dto.input.EstudianteInputDTO;
import com.adri.ej31.estudiante.infrastructure.dto.output.EstudianteFullOutputDTO;
import com.adri.ej31.estudiante.infrastructure.dto.output.EstudianteOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
            @RequestBody EstudianteInputDTO estudianteInputDTO
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

    @PutMapping("/addAsignatura/{id}")
    public EstudianteOutputDTO addAsignatura(
            @PathVariable("id") String id_estudiante,
            @RequestBody List<String> ids_asignaturas
    ){
        return  service.addAsignaturas(id_estudiante, ids_asignaturas);
    }
    @PutMapping("/removeAsignatura/{id}")
    public EstudianteOutputDTO removeAsignatura(
            @PathVariable("id") String id_estudiante,
            @RequestBody List<String> ids_asignaturas
    ){
        return  service.removeAsignaturas(id_estudiante, ids_asignaturas);
    }

}
