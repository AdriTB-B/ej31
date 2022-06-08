package com.adri.ej31.estudiante.domain;

import com.adri.ej31.estudiante.infrastructure.dto.input.EstudianteInputDTO;
import com.adri.ej31.estudio.domain.AsignaturaEntity;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.StringSequenceIdGenerator;
import com.adri.ej31.profesor.domain.ProfesorEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "estudiantes")
@Data
@NoArgsConstructor
public class EstudianteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
    @GenericGenerator(
            name = "idGenerator",
            strategy = "com.adri.ej31.StringSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "EST"),
                    @Parameter(name = StringSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    private String id_estudiante;

    @OneToOne
    @JoinColumn(name = "id_persona")
    private PersonaEntity persona;

    @Column(name = "horas_por_semana")
    private Integer num_hours_week;

    @Column(name = "comentarios")
    private String coments;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private ProfesorEntity profesor;

    @Column(name = "rama")
    private String rama;

    @ManyToMany
    @JoinTable(
            name = "estudiante_asignatura",
            joinColumns = @JoinColumn(name = "id_estudiante"),
            inverseJoinColumns = @JoinColumn(name = "id_study")
    )
    private List<AsignaturaEntity> asignaturas;

    public EstudianteEntity(EstudianteInputDTO estudiante){
        setComents(estudiante.getComents());
        setRama(estudiante.getRama());
        setNum_hours_week(estudiante.getNum_hours_week());
    }

    public void update(EstudianteInputDTO estudianteIn) {
        if(estudianteIn.getComents() != null){
            setComents(estudianteIn.getComents());
        }
        if(estudianteIn.getRama() != null){
            setRama(estudianteIn.getRama());
        }
        if(estudianteIn.getNum_hours_week() != null){
            setNum_hours_week(estudianteIn.getNum_hours_week());
        }
    }
}
