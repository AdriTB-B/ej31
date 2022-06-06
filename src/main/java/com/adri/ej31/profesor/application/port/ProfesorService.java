package com.adri.ej31.profesor.application.port;


import com.adri.ej31.profesor.domain.ProfesorEntity;

public interface ProfesorService {
    public ProfesorEntity findById(String id);

    ProfesorEntity save(ProfesorEntity profesor);
}
