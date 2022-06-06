package com.adri.ej31.estudiante.infrastructure.dto.input;

import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.profesor.domain.ProfesorEntity;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class EstudianteInputDTO {
    @NotEmpty(message = "El alumno debe tener unos datos personales")
    private String id_persona;
//    @NotEmpty(message = "El alumno debe tener un profesor/profesora")
//    private ProfesorEntity profesor;
    //@NotEmpty(message = "El alumno debe especificar el número de horas semanales")
    private Integer num_hours_week;
    private String coments;
    @NotEmpty(message = "El alumno debe pertenecer a una rama")
    private String rama;
}
