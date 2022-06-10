package com.adri.ej31.estudiante_asignatura.application.port;

import com.adri.ej31.estudiante_asignatura.infrastructure.dto.input.EstudianteAsignaturaInputDTO;
import com.adri.ej31.estudiante_asignatura.infrastructure.dto.output.EstudianteAsignaturaOutputDTO;

import java.util.List;

public interface EstudianteAsignaturaService {
    EstudianteAsignaturaOutputDTO save(EstudianteAsignaturaInputDTO asignatura);

    EstudianteAsignaturaOutputDTO update(String id, EstudianteAsignaturaInputDTO asignaturaIn);

    EstudianteAsignaturaOutputDTO findById(String id);

    void deleteById(String id);

    List<EstudianteAsignaturaOutputDTO> getAsignaturasByIdEstudiante(String id_estudiante);
}
