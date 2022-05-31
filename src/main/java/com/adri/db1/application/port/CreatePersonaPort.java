package com.adri.db1.application.port;

import com.adri.db1.infraestructure.dto.input.PersonaInputDTO;
import com.adri.db1.infraestructure.dto.output.PersonaOutputDTO;


public interface CreatePersonaPort {
    PersonaOutputDTO addPersona(PersonaInputDTO personaIn) throws Exception;
}
