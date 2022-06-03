package com.adri.ej31.application;

import com.adri.ej31.application.port.DeletePersonaPort;
import com.adri.ej31.infraestructure.exception.NotFoundException;
import com.adri.ej31.infraestructure.repository.PersonaRepository;
import com.adri.ej31.domain.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePersonaUseCase implements DeletePersonaPort {
    @Autowired
    PersonaRepository repository;

    @Override
    public void deletePersona(String id) throws NotFoundException {
        PersonaEntity persona = repository.findById(id)
                .orElseThrow(()-> new NotFoundException("No se encuentra ninguna persona con id " + id));
        repository.delete(persona);
    }
}
