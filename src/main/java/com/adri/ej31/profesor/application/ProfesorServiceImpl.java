package com.adri.ej31.profesor.application;

import com.adri.ej31.profesor.application.port.ProfesorService;
import com.adri.ej31.profesor.domain.ProfesorEntity;
import com.adri.ej31.profesor.infrastructure.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServiceImpl implements ProfesorService {
    @Autowired
    ProfesorRepository repository;
    @Override
    public ProfesorEntity findById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public ProfesorEntity save(ProfesorEntity profesor) {
        repository.save(profesor);
        return profesor;
    }
}
