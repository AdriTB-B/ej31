package com.adri.db1.application.port;

import com.adri.db1.infraestructure.dto.input.PersonaInputDTO;
import com.adri.db1.infraestructure.dto.output.PersonaOutputDTO;

public interface UpdatePersonaPort {
    PersonaOutputDTO updatePersona(Integer id, PersonaInputDTO personaIn);

}
