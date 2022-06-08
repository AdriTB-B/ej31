package com.adri.ej31.profesor.application.port;


import com.adri.ej31.profesor.domain.ProfesorEntity;
import com.adri.ej31.profesor.infrastructure.dto.input.ProfesorInputDTO;
import com.adri.ej31.profesor.infrastructure.dto.output.ProfesorOutputDTO;

public interface ProfesorService {
    public ProfesorOutputDTO findProfesorById(String id);

    ProfesorOutputDTO save(ProfesorInputDTO profesor);

    void deleteProfesor(String id);

    ProfesorOutputDTO updateProfesor(String id, ProfesorInputDTO profesorIn);
}
