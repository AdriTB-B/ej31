package com.adri.ej31.persona.application.port;

import com.adri.ej31.persona.domain.PersonaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReadPersonaPort {
    PersonaEntity getPersonaById(String id);
    List<PersonaEntity> getPersonaByName(String nombre) throws Exception;

    List<PersonaEntity> getPersonas();
    Page<PersonaEntity> getPersonasPageable(Pageable pageable);
}
