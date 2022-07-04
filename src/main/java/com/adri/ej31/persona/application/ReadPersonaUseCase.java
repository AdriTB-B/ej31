package com.adri.ej31.persona.application;

import com.adri.ej31.exception.NotFoundException;
import com.adri.ej31.persona.application.port.ReadPersonaPort;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infrastructure.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadPersonaUseCase implements ReadPersonaPort {

    private PersonaRepository personaRepo;
    @Autowired
    public ReadPersonaUseCase(PersonaRepository personaRepo){
        this.personaRepo = personaRepo;
    }
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
    public Page<PersonaEntity> getPersonasPageable(Pageable pageable) {
        return personaRepo.findAll(pageable);
    }

    @Override
    public List<PersonaEntity> getPersonas() {
        return personaRepo.findAll();
    }
}
