package com.adri.ej31.estudiante_asignatura.infrastructure.controller;

import com.adri.ej31.estudiante_asignatura.application.port.EstudianteAsignaturaService;
import com.adri.ej31.estudiante_asignatura.infrastructure.dto.input.EstudianteAsignaturaInputDTO;
import com.adri.ej31.estudiante_asignatura.infrastructure.dto.output.EstudianteAsignaturaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/asignatura")
public class EstudianteAsignaturaController {
    @Autowired
    EstudianteAsignaturaService estAsiService;
    @PostMapping("/add")
    public EstudianteAsignaturaOutputDTO addAsignatura(@Valid @RequestBody EstudianteAsignaturaInputDTO asignaturaIn){
        return estAsiService.save(asignaturaIn);
    }

    @PutMapping("/{id}")
    public EstudianteAsignaturaOutputDTO updateAsignatura(@PathVariable("id") String id, @RequestBody EstudianteAsignaturaInputDTO asignaturaIn){
        return estAsiService.update(id, asignaturaIn);
    }

    @GetMapping("/{id}")
    public EstudianteAsignaturaOutputDTO getAsignatura(@PathVariable("id") String id){
        return estAsiService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAsignatura(@PathVariable("id")String id){
        estAsiService.deleteById(id);
    }

    @GetMapping("/estudiante_estudio/{id}")
    public List<EstudianteAsignaturaOutputDTO> getAsignaturasByIdEstudiante(@PathVariable("id")String id_Estudiante){
        return estAsiService.getAsignaturasByIdEstudiante(id_Estudiante);
    }
}
