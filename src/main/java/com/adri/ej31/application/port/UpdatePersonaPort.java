package com.adri.ej31.application.port;

import com.adri.ej31.infraestructure.dto.input.PersonaInputDTO;
import com.adri.ej31.infraestructure.dto.output.PersonaOutputDTO;

public interface UpdatePersonaPort {
    PersonaOutputDTO updatePersona(String id, PersonaInputDTO personaIn);

}
