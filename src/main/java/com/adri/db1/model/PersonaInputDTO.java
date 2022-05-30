package com.adri.db1.model;

import lombok.Data;


@Data
public class PersonaInputDTO {
    private String usuario;
    private String name;
    private String password;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private String imagen_url;

}
