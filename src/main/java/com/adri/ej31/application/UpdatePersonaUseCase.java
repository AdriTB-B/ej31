package com.adri.ej31.application;

import com.adri.ej31.application.port.UpdatePersonaPort;
import com.adri.ej31.infraestructure.exception.NotFoundException;
import com.adri.ej31.infraestructure.repository.PersonaRepository;
import com.adri.ej31.domain.PersonaEntity;
import com.adri.ej31.infraestructure.dto.input.PersonaInputDTO;
import com.adri.ej31.infraestructure.dto.output.PersonaOutputDTO;
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
