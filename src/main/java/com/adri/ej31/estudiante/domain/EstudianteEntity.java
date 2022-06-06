package com.adri.ej31.estudiante.domain;

import com.adri.ej31.estudiante.infrastructure.dto.input.EstudianteInputDTO;
import com.adri.ej31.estudiante_estudio.domain.Estudiante_EstudioEntity;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.profesor.domain.ProfesorEntity;
import com.adri.ej31.StringSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

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
                    @Parameter(name = StringSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "STU"),
                    @Parameter(name = StringSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    private String id_estudiante;

    @OneToOne
    @JoinColumn(name = "id_persona" ,unique = true)
    private PersonaEntity persona;

    @Column(name = "horas_por_semana")
    private Integer num_hours_week;

    @Column(name = "comentarios")
    private String coments;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_profesor")
//    private ProfesorEntity profesor;

    @Column(name = "rama")
    private String rama;

//    @OneToMany(mappedBy = "id_study")
//    private List<Estudiante_EstudioEntity> estudios;

    public EstudianteEntity(EstudianteInputDTO estudiante){
        setComents(estudiante.getComents());
        setRama(estudiante.getRama());
        setNum_hours_week(estudiante.getNum_hours_week());
    }

    public void update(EstudianteInputDTO estudianteIn) {
        setComents(estudianteIn.getComents());
        setRama(estudianteIn.getRama());
        setNum_hours_week(estudianteIn.getNum_hours_week());
    }
}