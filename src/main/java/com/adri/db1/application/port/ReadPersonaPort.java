package com.adri.db1.application.port;

import com.adri.db1.infraestructure.dto.output.PersonaOutputDTO;

import java.util.List;

public interface ReadPersonaPort {
    PersonaOutputDTO getPersonaById(Integer id) throws Exception;
    List<PersonaOutputDTO> getPersonaByName(String nombre) throws Exception;
    List<PersonaOutputDTO> getPersonas();
}
