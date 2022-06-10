package com.adri.ej31.estudiante_asignatura.infrastructure.dto.input;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class EstudianteAsignaturaInputDTO {
    @NotNull(message = "La asignatura debe tener un profesor asignado")
    private String id_profesor;
    @NotNull(message = "La asignatura debe tener un nombre")
    private String name;
    private String comment;
    private Date initial_date;
    private Date finish_date;
    private List<String> ids_estudiantes;
}
