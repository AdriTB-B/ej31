package com.adri.ej31.estudiante.infrastructure.dto.input;

import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EstudianteInputDTO {
    @NotEmpty(message = "El alumno debe tener unos datos personales")
    private String id_persona;
//    @NotEmpty(message = "El alumno debe tener un profesor/profesora")
//    private ProfesorEntity profesor;
    @NotNull
    private Integer num_hours_week;
    private String coments;
    @NotEmpty(message = "El alumno debe pertenecer a una rama")
    private String rama;
}
