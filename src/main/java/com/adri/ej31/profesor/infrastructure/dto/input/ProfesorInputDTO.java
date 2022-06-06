package com.adri.ej31.profesor.infrastructure.dto.input;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ProfesorInputDTO {
    @NotEmpty(message = "El profesor debe tener unos datos personales")
    private String id_persona;
    private String coments;
    @NotEmpty(message = "El profesor debe pertenecer a una rama")
    private String rama;
}
