package com.adri.db1.application;

import com.adri.db1.application.port.DeletePersonaPort;
import com.adri.db1.infraestructure.exception.NotFoundException;
import com.adri.db1.infraestructure.repository.PersonaRepository;
import com.adri.db1.domain.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

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
