package com.adri.ej31.persona.infraestructure.dto.output;

import com.adri.ej31.estudiante.infrastructure.dto.output.EstudianteOutputDTO;
import com.adri.ej31.exception.IncorrectRolException;
import com.adri.ej31.persona.domain.PersonaEntity;
import lombok.Data;

@Data
public class PersonaEstudianteOutputDTO extends PersonaOutputDTO{
    private EstudianteOutputDTO estudiante;
    public PersonaEstudianteOutputDTO(PersonaEntity persona) {
        super(persona);
        if(persona.getRolEstudiante() != null){
            setEstudiante(new EstudianteOutputDTO(persona.getRolEstudiante()));
        }else{
            throw new IncorrectRolException("La persona con id " + persona.getId_persona() + " no es un estudiante");
        }
    }
}
