package com.adri.ej31.persona.application.port;

import com.adri.ej31.persona.infraestructure.dto.output.PersonaOutputDTO;

import java.util.List;

public interface ReadPersonaPort {
    PersonaOutputDTO getPersonaById(String id);
    List<PersonaOutputDTO> getPersonaByName(String nombre) throws Exception;
    List<PersonaOutputDTO> getPersonas();
}
