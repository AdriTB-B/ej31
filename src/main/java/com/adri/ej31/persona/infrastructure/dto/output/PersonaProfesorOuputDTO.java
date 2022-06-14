package com.adri.ej31.persona.infrastructure.dto.output;

import com.adri.ej31.exception.IncorrectRolException;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.profesor.infrastructure.dto.output.ProfesorOutputDTO;
import lombok.Data;

@Data
public class PersonaProfesorOuputDTO extends PersonaOutputDTO{
    private ProfesorOutputDTO profesor;
    public PersonaProfesorOuputDTO(PersonaEntity persona) {
        super(persona);
        if(persona.getRolProfesor() != null){
            setProfesor(new ProfesorOutputDTO(persona.getRolProfesor()));
        }else{
            throw new IncorrectRolException("La persona con id " + persona.getId_persona() + " no es un profesor");
        }
    }
}
