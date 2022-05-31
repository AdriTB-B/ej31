package com.adri.db1.application;

import com.adri.db1.application.port.CreatePersonaPort;
import com.adri.db1.domain.IPersonaRepository;
import com.adri.db1.domain.PersonaEntity;
import com.adri.db1.infraestructure.dto.input.PersonaInputDTO;
import com.adri.db1.infraestructure.dto.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CreatePersonaUseCase implements CreatePersonaPort {
    @Autowired
    IPersonaRepository repository;

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaIn) throws Exception {
        PersonaEntity persona = new PersonaEntity(personaIn);
        repository.save(persona);
        return new PersonaOutputDTO(persona);
    }
}
