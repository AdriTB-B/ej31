package com.adri.db1;

import com.adri.db1.model.PersonaEntity;
import com.adri.db1.model.PersonaInputDTO;
import com.adri.db1.model.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService{
    @Autowired
    IPersonaRepository repository;

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaIn) throws Exception {
        PersonaEntity persona = new PersonaEntity(personaIn);
        repository.save(persona);
        return new PersonaOutputDTO(persona);
    }

    @Override
    public PersonaOutputDTO getPersonaById(Integer id) throws Exception {
        PersonaEntity persona = repository.findById(id)
                .orElseThrow(() -> new Exception("No se ha encontrado ninguna persona con id " + id));
        return new PersonaOutputDTO(persona);
    }

    @Override
    public List<PersonaOutputDTO> getPersonaByName(String nombre) throws Exception {
        PersonaEntity personaToFind = new PersonaEntity();
        personaToFind.setName(nombre);
        List<PersonaOutputDTO> listaSalida = new ArrayList<>();
        repository.findAll(Example.of(personaToFind)).forEach(pE->{
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

    @Override
    public String updatePersona(PersonaInputDTO persona) throws Exception {
        return null;
    }

    @Override
    public String deletePersona(int id) throws Exception {
        return null;
    }
}
