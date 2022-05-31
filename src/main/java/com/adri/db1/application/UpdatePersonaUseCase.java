package com.adri.db1.application;

import com.adri.db1.application.port.UpdatePersonaPort;
import com.adri.db1.domain.IPersonaRepository;
import com.adri.db1.domain.PersonaEntity;
import com.adri.db1.infraestructure.dto.input.PersonaInputDTO;
import com.adri.db1.infraestructure.dto.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

@Service
public class UpdatePersonaUseCase implements UpdatePersonaPort {
    @Autowired
    IPersonaRepository repository;

    @Override
    public PersonaOutputDTO updatePersona(Integer id, PersonaInputDTO personaIn) throws Exception {
        PersonaEntity personaToUpdate = repository.findById(id)
                .orElseThrow(()->new HttpMediaTypeNotAcceptableException(
                        "No se ha encontrado ninguna persona con id " + id + " a la que actualizar los datos"
                ));
        personaToUpdate.update(personaIn);
        repository.save(personaToUpdate);
        return new PersonaOutputDTO(personaToUpdate);
    }
}
