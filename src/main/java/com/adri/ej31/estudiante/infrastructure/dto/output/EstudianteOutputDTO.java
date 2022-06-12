package com.adri.ej31.estudiante.infrastructure.dto.output;

import com.adri.ej31.estudiante.domain.EstudianteEntity;
import com.adri.ej31.estudiante_asignatura.domain.EstudianteAsignaturaEntity;
import com.adri.ej31.profesor.infrastructure.dto.output.ProfesorOutputDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EstudianteOutputDTO {
    private String id_estudiante;
    private String id_persona;
    private String id_profesor;
    private Integer num_hours_week;
    private String coments;
    private String rama;
    private List<String> ids_asignaturas;

    public EstudianteOutputDTO(EstudianteEntity estudiante){
        setId_estudiante(estudiante.getId_estudiante());
        setId_persona(estudiante.getPersona().getId_persona());
        setComents(estudiante.getComents());
        setRama(estudiante.getRama());
        setNum_hours_week(estudiante.getNum_hours_week());
        setId_profesor(estudiante.getProfesor().getId_profesor());
        setIds_asignaturas(estudiante.getAsignaturas().stream()
                .map(EstudianteAsignaturaEntity::getId_asignatura)
                .toList()
        );
    }
}
