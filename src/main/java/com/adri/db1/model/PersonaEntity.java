package com.adri.db1.model;

import lombok.Builder;
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
    @Column @NotNull
    private String usuario;
    @Column @NotNull
    private String password;
    @Column @NotNull
    private String name;
    @Column
    private String surname;
    @Column @NotNull
    private String company_email;
    @Column @NotNull
    private String personal_email;
    @Column @NotNull
    private String city;
    @Column @NotNull
    private Boolean active;
    @Column @NotNull
    private Date created_date;
    @Column
    private String imagen_url;
    @Column
    private Date termination_date;

    public PersonaEntity(PersonaInputDTO personaIn) throws Exception{
        if(personaIn.getUsuario() != null
                && personaIn.getUsuario().length() <= 10
                && personaIn.getUsuario().length() >= 6
        ){
            setUsuario(personaIn.getUsuario());
        }else{
            throw new Exception("El campo usuario debe tener entre 6 y 10 caracteres");
        }
        if(personaIn.getName() == null){
            throw new Exception("El campo nombre no puede estar vacío");
        }else{
            setName(personaIn.getName());
        }
        if(personaIn.getPassword() == null){
            throw new Exception("El campo contraseña no puede estar vacío");
        }else{
            setPassword(personaIn.getPassword());
        }
        if(personaIn.getCompany_email() == null){
            throw new Exception("El campo email de compañia no puede estar vacío");
        }else{
            setCompany_email(personaIn.getCompany_email());
        }
        if(personaIn.getPersonal_email() == null){
            throw new Exception("El campo email personal no puede estar vacío");
        }else{
            setPersonal_email(personaIn.getPersonal_email());
        }
        if(personaIn.getCity() == null){
            throw new Exception("El campo ciudad no puede estar vacío");
        }else{
            setCity(personaIn.getCity());
        }
        setCreated_date(new Date());
        setActive(true);
        setImagen_url(personaIn.getImagen_url());
        setSurname(personaIn.getSurname());
    }
}
