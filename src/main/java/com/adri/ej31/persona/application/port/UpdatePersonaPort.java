package com.adri.ej31.persona.application.port;

import com.adri.ej31.persona.infrastructure.dto.input.PersonaInputDTO;
import com.adri.ej31.persona.infrastructure.dto.output.PersonaOutputDTO;

public interface UpdatePersonaPort {
    PersonaOutputDTO updatePersona(String id, PersonaInputDTO personaIn);

}
