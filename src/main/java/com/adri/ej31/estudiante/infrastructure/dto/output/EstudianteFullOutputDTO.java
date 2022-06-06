package com.adri.ej31.estudiante.infrastructure.dto.output;

import com.adri.ej31.estudiante.domain.EstudianteEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EstudianteFullOutputDTO extends EstudianteOutputDTO{
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

    public EstudianteFullOutputDTO(EstudianteEntity estudiante) {
        super(estudiante);
        setId_persona(estudiante.getPersona().getId_persona());
        setUsuario(estudiante.getPersona().getUsuario());
        setName(estudiante.getPersona().getName());
        setSurname(estudiante.getPersona().getSurname());
        setCompany_email(estudiante.getPersona().getCompany_email());
        setPersonal_email(estudiante.getPersona().getPersonal_email());
        setCity(estudiante.getPersona().getCity());
        setActive(estudiante.getPersona().getActive());
        setCreated_date(estudiante.getPersona().getCreated_date());
        setImagen_url(estudiante.getPersona().getImagen_url());
        setTermination_date(estudiante.getPersona().getTermination_date());

    }
}
