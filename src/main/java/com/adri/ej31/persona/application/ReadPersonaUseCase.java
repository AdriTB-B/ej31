package com.adri.ej31.persona.application;

import com.adri.ej31.exception.NotFoundException;
import com.adri.ej31.persona.application.port.ReadPersonaPort;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infraestructure.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadPersonaUseCase implements ReadPersonaPort {
    @Autowired
    PersonaRepository personaRepo;

    @Override
    public PersonaEntity getPersonaById(String id) throws NotFoundException {
        PersonaEntity persona = personaRepo.findById(id)
                .orElseThrow(()-> new NotFoundException("No se encuentra ninguna persona con id " + id));
        return persona;
    }

    @Override
    public List<PersonaEntity> getPersonaByName(String nombre) {
        return personaRepo.findByName(nombre);
    }

    @Override
    public List<PersonaEntity> getPersonas() {
        return personaRepo.findAll();
    }
}
