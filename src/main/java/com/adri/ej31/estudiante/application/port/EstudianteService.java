package com.adri.ej31.estudiante.application.port;

import com.adri.ej31.estudiante.domain.EstudianteEntity;
import com.adri.ej31.estudiante.infrastructure.dto.input.EstudianteInputDTO;
import com.adri.ej31.estudiante.infrastructure.dto.output.EstudianteOutputDTO;

public interface EstudianteService {
    public EstudianteEntity findById(String id);

    EstudianteOutputDTO save(EstudianteInputDTO estudiante);

    void deleteById(String id);
}
