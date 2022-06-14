package com.adri.ej31.persona.application;

import com.adri.ej31.exception.NotFoundException;
import com.adri.ej31.persona.application.port.UpdatePersonaPort;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infrastructure.dto.input.PersonaInputDTO;
import com.adri.ej31.persona.infrastructure.dto.output.PersonaOutputDTO;
import com.adri.ej31.persona.infrastructure.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatePersonaUseCase implements UpdatePersonaPort {
    @Autowired
    PersonaRepository repository;

    @Override
    public PersonaOutputDTO updatePersona(String id, PersonaInputDTO personaIn) throws NotFoundException {
        PersonaEntity personaToUpdate = repository.findById(id)
                .orElseThrow(()-> new NotFoundException("No se encuentra ninguna persona con id " + id));
        personaToUpdate.update(personaIn);
        repository.save(personaToUpdate);
        return new PersonaOutputDTO(personaToUpdate);
    }
}
