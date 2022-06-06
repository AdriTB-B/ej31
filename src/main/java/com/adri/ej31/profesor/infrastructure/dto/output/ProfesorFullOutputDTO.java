package com.adri.ej31.profesor.infrastructure.dto.output;

import com.adri.ej31.profesor.domain.ProfesorEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ProfesorFullOutputDTO extends ProfesorOutputDTO{
    private String id_persona;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;

    public ProfesorFullOutputDTO(ProfesorEntity profesor) {
        super(profesor);
        setId_persona(profesor.getPersona().getId_persona());
        setUsuario(profesor.getPersona().getUsuario());
        setName(profesor.getPersona().getName());
        setSurname(profesor.getPersona().getSurname());
        setCompany_email(profesor.getPersona().getCompany_email());
        setPersonal_email(profesor.getPersona().getPersonal_email());
        setCity(profesor.getPersona().getCity());
        setActive(profesor.getPersona().getActive());
        setCreated_date(profesor.getPersona().getCreated_date());
        setImagen_url(profesor.getPersona().getImagen_url());
        setTermination_date(profesor.getPersona().getTermination_date());

    }
}
