package com.adri.ej31.profesor.domain;

import com.adri.ej31.estudiante.domain.EstudianteEntity;
import com.adri.ej31.estudio.domain.AsignaturaEntity;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.StringSequenceIdGenerator;
import com.adri.ej31.profesor.infrastructure.dto.input.ProfesorInputDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "profesores")
@Data
@NoArgsConstructor
public class ProfesorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
    @GenericGenerator(
            name = "idGenerator",
            strategy = "com.adri.ej31.StringSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PRO"),
                    @Parameter(name = StringSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    private String id_profesor;

    @OneToOne
    @JoinColumn(name = "id_persona")
    private PersonaEntity persona;

    @Column(name = "coments")
    private String coments;

    @Column(name = "branch") @NotNull
    private String rama;

//    @OneToMany(mappedBy = "profesor")
//    private List<AsignaturaEntity> estudios;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    private Set<EstudianteEntity> estudiantes;

    public ProfesorEntity(ProfesorInputDTO profesor){
        setComents(profesor.getComents());
        setRama(profesor.getRama());
    }

    public void update(ProfesorInputDTO profesorIn) {
        if(profesorIn.getRama() != null){
            setRama(profesorIn.getRama());
        }
        if(profesorIn.getComents() != null){
            setComents(profesorIn.getComents());
        }
    }
}