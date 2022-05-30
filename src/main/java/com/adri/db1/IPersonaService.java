package com.adri.db1;

import com.adri.db1.model.PersonaInputDTO;
import com.adri.db1.model.PersonaOutputDTO;

import java.util.List;

public interface IPersonaService {
    //CREATE
    PersonaOutputDTO addPersona(PersonaInputDTO personaIn) throws Exception;

    //READ
    PersonaOutputDTO getPersonaById(Integer id) throws Exception;
    PersonaOutputDTO getPersonaByName(String nombre) throws Exception;
    List<PersonaOutputDTO> getPersonas();

    //UPDATE
    String updatePersona(PersonaInputDTO personaIn) throws Exception;

    //DELETE
    String deletePersona(int id) throws Exception;
}
