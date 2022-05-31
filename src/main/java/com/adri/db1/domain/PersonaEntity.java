package com.adri.db1.domain;

import com.adri.db1.infraestructure.dto.input.PersonaInputDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@NoArgsConstructor
@Entity
@Table(name = "Personas")
public class PersonaEntity {
    @Id
    @GeneratedValue
    private Integer id_persona;
    @Column
    private String usuario;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String company_email;
    @Column
    private String personal_email;
    @Column
    private String city;
    @Column
    private Boolean active;
    @Column
    private Date created_date;
    @Column
    private String imagen_url;
    @Column
    private Date termination_date;

    public PersonaEntity(PersonaInputDTO personaIn) throws Exception {
        //Validaciones en en PersonaInputDTO
        setUsuario(personaIn.getUsuario());
        setName(personaIn.getName());
        setPassword(personaIn.getPassword());
        setCompany_email(personaIn.getCompany_email());
        setPersonal_email(personaIn.getPersonal_email());
        setCity(personaIn.getCity());
        setCreated_date(new Date());
        setActive(true);
        setImagen_url(personaIn.getImagen_url());
        setSurname(personaIn.getSurname());
    }

    public void update(PersonaInputDTO personaIn) throws Exception {
        if (personaIn.getUsuario() != null
                && personaIn.getUsuario().length() <= 10
                && personaIn.getUsuario().length() >= 6
        ) {
            setUsuario(personaIn.getUsuario());
        }
        if (personaIn.getName() != null) {
            setName(personaIn.getName());
        }
        if (personaIn.getPassword() != null) {
            setPassword(personaIn.getPassword());
        }
        if (personaIn.getCompany_email() != null) {
            setCompany_email(personaIn.getCompany_email());
        }
        if (personaIn.getPersonal_email() != null) {
            setPersonal_email(personaIn.getPersonal_email());
        }
        if (personaIn.getCity() != null) {
            setCity(personaIn.getCity());
        }
        if (personaIn.getSurname() != null) {
            setSurname(personaIn.getSurname());
        }
        if (personaIn.getImagen_url() != null) {
            setImagen_url(personaIn.getImagen_url());
        }
    }
}
