package com.adri.ej31.estudiante_asignatura.domain;

import com.adri.ej31.StringSequenceIdGenerator;
import com.adri.ej31.estudiante.domain.EstudianteEntity;
import com.adri.ej31.estudiante_asignatura.infrastructure.dto.input.EstudianteAsignaturaInputDTO;
import com.adri.ej31.profesor.domain.ProfesorEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "asignaturas")
@Data
@NoArgsConstructor
public class EstudianteAsignaturaEntity {
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
    private String id_asignatura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    private ProfesorEntity profesor;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "estudiante_estudio",
            joinColumns = @JoinColumn(name = "id_estudiante", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_asignatura", nullable = false)
    )
    private List<EstudianteEntity> estudiantes = new ArrayList<>();

    @Column(name = "nombre")
    private String name;

    @Column(name = "comentarios")
    private String comment;

    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date initial_date;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date finish_date;

    public EstudianteAsignaturaEntity(EstudianteAsignaturaInputDTO estAsiIn){
        setName(estAsiIn.getName());
        setComment(estAsiIn.getComment());
        setInitial_date(estAsiIn.getInitial_date());
        setFinish_date(estAsiIn.getFinish_date());
    }

    public void update(EstudianteAsignaturaInputDTO asignaturaIn) {
        if(asignaturaIn.getName() != null){
            setName(asignaturaIn.getName());
        }
        if(asignaturaIn.getInitial_date() != null){
            setInitial_date(asignaturaIn.getInitial_date());
        }
        if(asignaturaIn.getFinish_date() != null){
            setFinish_date(asignaturaIn.getFinish_date());
        }
    }

    public void addEstudiante(EstudianteEntity estudiante) {
        estudiantes.add(estudiante);
    }

    public void removeEstudiante(EstudianteEntity estudiante) {
        estudiantes.remove(estudiante);
    }
}

