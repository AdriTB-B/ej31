package com.adri.db1.application.port;

import com.adri.db1.infraestructure.dto.output.PersonaOutputDTO;
import com.adri.db1.infraestructure.exception.NotFoundException;

import java.util.List;

public interface ReadPersonaPort {
    PersonaOutputDTO getPersonaById(String id);
    List<PersonaOutputDTO> getPersonaByName(String nombre) throws Exception;
    List<PersonaOutputDTO> getPersonas();
}
