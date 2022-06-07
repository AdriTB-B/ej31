package com.adri.ej31.persona.application.port;

import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infraestructure.dto.output.PersonaOutputDTO;

import java.lang.reflect.Type;
import java.util.List;

public interface ReadPersonaPort {
    PersonaEntity getPersonaById(String id);
    List<PersonaOutputDTO> getPersonaByName(String nombre) throws Exception;
    List<PersonaOutputDTO> getPersonas();
}
