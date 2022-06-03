package com.adri.ej31.application;

import com.adri.ej31.application.port.CreatePersonaPort;
import com.adri.ej31.infraestructure.repository.PersonaRepository;
import com.adri.ej31.domain.PersonaEntity;
import com.adri.ej31.infraestructure.dto.input.PersonaInputDTO;
import com.adri.ej31.infraestructure.dto.output.PersonaOutputDTO;
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
