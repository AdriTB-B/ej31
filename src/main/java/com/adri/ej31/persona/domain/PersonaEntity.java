package com.adri.ej31.persona.domain;

import com.adri.ej31.StringSequenceIdGenerator;
import com.adri.ej31.estudiante.domain.EstudianteEntity;
import com.adri.ej31.persona.infraestructure.dto.input.PersonaInputDTO;
import com.adri.ej31.profesor.domain.ProfesorEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;


@Data
@NoArgsConstructor
@Entity
@Table(name = "personas")
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
    @GenericGenerator(
            name = "idGenerator",
            strategy = "com.adri.ej31.StringSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PER"),
                    @Parameter(name = StringSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    private String id_persona;
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
    @Temporal(TemporalType.DATE)
    private Date created_date;
    @Column
    private String imagen_url;
    @Column
    @Temporal(TemporalType.DATE)
    private Date termination_date;

    @OneToOne(mappedBy = "persona")
    private ProfesorEntity rolProfesor;
    @OneToOne(mappedBy = "persona")
    private EstudianteEntity rolEstudiante;

    public PersonaEntity(PersonaInputDTO personaIn) {
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

    public void update(PersonaInputDTO personaIn) {
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
