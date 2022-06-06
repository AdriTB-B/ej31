package com.adri.ej31.persona.application;

import com.adri.ej31.persona.application.port.CreatePersonaPort;
import com.adri.ej31.persona.infraestructure.repository.PersonaRepository;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infraestructure.dto.input.PersonaInputDTO;
import com.adri.ej31.persona.infraestructure.dto.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePersonaUseCase implements CreatePersonaPort {
    @Autowired
    PersonaRepository repository;

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaIn){
        PersonaEntity persona = new PersonaEntity(personaIn);
        repository.save(persona);
        return new PersonaOutputDTO(persona);
    }
}
