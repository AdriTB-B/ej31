package com.adri.db1.application;

import com.adri.db1.application.port.ReadPersonaPort;
import com.adri.db1.domain.IPersonaRepository;
import com.adri.db1.domain.PersonaEntity;
import com.adri.db1.infraestructure.dto.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReadPersonaUseCase implements ReadPersonaPort {
    @Autowired
    IPersonaRepository repository;

    @Override
    public PersonaOutputDTO getPersonaById(Integer id) throws Exception {
        PersonaEntity persona = repository.findById(id)
                .orElseThrow(() -> new HttpServerErrorException( //Lanza un 500 y debe lanzar un 404
                        HttpStatus.NOT_FOUND,
                        "No se ha encontrado ninguna persona con id " + id
                ));
        return new PersonaOutputDTO(persona);
    }

    @Override
    public List<PersonaOutputDTO> getPersonaByName(String nombre) throws Exception {
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
