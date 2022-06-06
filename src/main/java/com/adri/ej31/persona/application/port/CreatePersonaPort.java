package com.adri.ej31.persona.application.port;

import com.adri.ej31.persona.infraestructure.dto.input.PersonaInputDTO;
import com.adri.ej31.persona.infraestructure.dto.output.PersonaOutputDTO;


public interface CreatePersonaPort {
    PersonaOutputDTO addPersona(PersonaInputDTO personaIn);
}
