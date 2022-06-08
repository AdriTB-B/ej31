package com.adri.ej31.estudio.domain;

import com.adri.ej31.StringSequenceIdGenerator;
import com.adri.ej31.estudiante.domain.EstudianteEntity;
import com.adri.ej31.profesor.domain.ProfesorEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "asignaturas")
@Data
@NoArgsConstructor
public class AsignaturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
    @GenericGenerator(
            name = "idGenerator",
            strategy = "com.adri.ej31.StringSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ASI"),
                    @Parameter(name = StringSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    private Integer id_study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    private ProfesorEntity profesor;

    @ManyToMany(mappedBy = "asignaturas")
    private Set<EstudianteEntity> estudiantes;

    @Column(name = "asignatura")
    private String asignatura;

    @Column(name = "comentarios")
    private String comment;

    @Column(name = "initial_date")
    @Temporal(TemporalType.DATE)
    private Date initial_date;

    @Column(name = "finish_date")
    @Temporal(TemporalType.DATE)
    private Date finish_date;
}

