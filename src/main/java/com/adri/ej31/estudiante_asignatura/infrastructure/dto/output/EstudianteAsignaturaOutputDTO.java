package com.adri.ej31.estudiante_asignatura.infrastructure.dto.output;

import com.adri.ej31.estudiante.domain.EstudianteEntity;
import com.adri.ej31.estudiante_asignatura.domain.EstudianteAsignaturaEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class EstudianteAsignaturaOutputDTO {
    private String id_asignaruta;
    private String id_profesor;
    private List<String> ids_estudiantes;
    private String name;
    private String comment;
    private Date initial_date;
    private Date finish_date;

    public EstudianteAsignaturaOutputDTO(EstudianteAsignaturaEntity asignatura){
        setId_asignaruta(asignatura.getId_asignatura());
        setComment(asignatura.getComment());
        setName(asignatura.getName());
        setFinish_date(asignatura.getFinish_date());
        setInitial_date(asignatura.getInitial_date());
        setId_profesor(asignatura.getProfesor().getId_profesor());
        setIds_estudiantes(asignatura.getEstudiantes().stream()
                .map(EstudianteEntity::getId_estudiante)
                .collect(Collectors.toList())
        );

    }
}
