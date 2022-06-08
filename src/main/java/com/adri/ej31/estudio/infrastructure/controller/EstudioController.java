package com.adri.ej31.estudio.infrastructure.controller;

import com.adri.ej31.estudio.infrastructure.dto.input.AsignaturaInputDTO;
import com.adri.ej31.estudio.infrastructure.dto.output.AsignaturaOutputDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/estudio")
public class EstudioController {
    @PostMapping("/add")
    public AsignaturaOutputDTO addEstudio(@Valid @RequestBody AsignaturaInputDTO estudio){
        return null;
    }
}
