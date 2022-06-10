package com.adri.ej31.estudiante.application.port;

import com.adri.ej31.estudiante.domain.EstudianteEntity;
import com.adri.ej31.estudiante.infrastructure.dto.input.EstudianteInputDTO;
import com.adri.ej31.estudiante.infrastructure.dto.output.EstudianteOutputDTO;

import java.util.List;

public interface EstudianteService {
    public EstudianteEntity findEstudianteById(String id);

    EstudianteOutputDTO save(EstudianteInputDTO estudiante);

    void deleteById(String id);

    EstudianteOutputDTO update(String id, EstudianteInputDTO estudianteInputDTO);

    EstudianteOutputDTO addAsignaturas(String id_estudiante, List<String> ids_asignaturas);
}
