package com.adri.ej31.persona.application;

import com.adri.ej31.persona.application.port.CreatePersonaPort;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infrastructure.dto.input.PersonaInputDTO;
import com.adri.ej31.persona.infrastructure.dto.output.PersonaOutputDTO;
import com.adri.ej31.persona.infrastructure.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePersonaUseCase implements CreatePersonaPort {
    private PersonaRepository repository;
    @Autowired
    public CreatePersonaUseCase(PersonaRepository repository){
        this.repository = repository;
    }

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaIn){
        PersonaEntity persona = new PersonaEntity(personaIn);
        repository.save(persona);
        return new PersonaOutputDTO(persona);
    }
}
