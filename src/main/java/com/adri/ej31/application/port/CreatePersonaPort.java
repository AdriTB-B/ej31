package com.adri.ej31.application.port;

import com.adri.ej31.infraestructure.dto.input.PersonaInputDTO;
import com.adri.ej31.infraestructure.dto.output.PersonaOutputDTO;


public interface CreatePersonaPort {
    PersonaOutputDTO addPersona(PersonaInputDTO personaIn);
}
