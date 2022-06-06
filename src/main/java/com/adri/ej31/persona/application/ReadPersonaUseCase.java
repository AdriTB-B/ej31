package com.adri.ej31.persona.application;

import com.adri.ej31.persona.application.port.ReadPersonaPort;
import com.adri.ej31.exception.NotFoundException;
import com.adri.ej31.persona.infraestructure.repository.PersonaRepository;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infraestructure.dto.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ReadPersonaUseCase implements ReadPersonaPort {
    @Autowired
    PersonaRepository repository;

    @Override
    public PersonaOutputDTO getPersonaById(String id) throws NotFoundException {
        PersonaEntity persona = repository.findById(id)
                .orElseThrow(()-> new NotFoundException("No se encuentra ninguna persona con id " + id));
        return new PersonaOutputDTO(persona);
    }

    @Override
    public List<PersonaOutputDTO> getPersonaByName(String nombre) {
        List<PersonaOutputDTO> listaSalida = new ArrayList<>();
        repository.findByName(nombre).forEach(pE->{
            listaSalida.add(new PersonaOutputDTO(pE));
        });
        return listaSalida;
    }

    @Override
    public List<PersonaOutputDTO> getPersonas() {
        List<PersonaOutputDTO> listaPersonaOut = new ArrayList<>();
        repository.findAll().forEach(p->{
            PersonaOutputDTO pOut = new PersonaOutputDTO(p);
            listaPersonaOut.add(pOut);
        });
        return listaPersonaOut;
    }
}
