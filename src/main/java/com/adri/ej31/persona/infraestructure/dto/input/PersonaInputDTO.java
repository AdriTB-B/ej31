package com.adri.ej31.persona.infraestructure.dto.input;

import lombok.Data;

import javax.validation.constraints.*;


@Data
public class PersonaInputDTO {
    @NotEmpty(message = "El usuario no puede estar vacío")
    @Size(min = 6, max = 10, message = "Debe tener mínimo 6 caracteres y máximo 10")
    private String usuario;
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String name;
    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String password;
    private String surname;
    @NotEmpty(message = "El email corporativo no puede estar vacío")
    @Email(message = "Email corporativo no es un email válido")
    private String company_email;
    @NotEmpty(message = "El email personal no puede estar vacío")
    @Email(message = "Email personal no es un email válido")
    private String personal_email;
    @NotEmpty(message = "La ciudad no puede estar vacía")
    private String city;
    private String imagen_url;
}
