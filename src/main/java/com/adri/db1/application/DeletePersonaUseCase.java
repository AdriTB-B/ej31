package com.adri.db1.application;

import com.adri.db1.application.port.DeletePersonaPort;
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
    public void deletePersona(Integer id) throws Exception {
        PersonaEntity persona = repository.findById(id)
                .orElseThrow(()->new HttpMediaTypeNotAcceptableException(
                        "No se ha encontrado ninguna persona con id " + id + " que eliminar"
                ));
        repository.delete(persona);
    }
}
