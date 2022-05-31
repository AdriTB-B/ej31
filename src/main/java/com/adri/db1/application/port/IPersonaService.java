package com.adri.db1.application.port;

import com.adri.db1.infraestructure.dto.input.PersonaInputDTO;
import com.adri.db1.infraestructure.dto.output.PersonaOutputDTO;

import java.util.List;

public interface IPersonaService {
    //CREATE
    PersonaOutputDTO addPersona(PersonaInputDTO personaIn) throws Exception;

    //READ
    PersonaOutputDTO getPersonaById(Integer id) throws Exception;
    List<PersonaOutputDTO> getPersonaByName(String nombre) throws Exception;
    List<PersonaOutputDTO> getPersonas();

    //UPDATE
    PersonaOutputDTO updatePersona(Integer id, PersonaInputDTO personaIn) throws Exception;

    //DELETE
    void deletePersona(Integer id) throws Exception;
}
