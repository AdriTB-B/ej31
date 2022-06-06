package com.adri.ej31.profesor.application.port;


import com.adri.ej31.profesor.domain.ProfesorEntity;
import com.adri.ej31.profesor.infrastructure.dto.input.ProfesorInputDTO;
import com.adri.ej31.profesor.infrastructure.dto.output.ProfesorOutputDTO;

public interface ProfesorService {
    public ProfesorEntity findById(String id);

    ProfesorOutputDTO save(ProfesorInputDTO profesor);
}