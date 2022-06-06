package com.adri.ej31.estudiante.infrastructure.dto.output;

import com.adri.ej31.estudiante.domain.EstudianteEntity;
import com.adri.ej31.persona.domain.PersonaEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstudianteOutputDTO {
    private String id_estudiante;
    private String id_persona;
    //private ProfesorEntity profesor;
    private Integer num_hours_week;
    private String coments;
    private String rama;

    public EstudianteOutputDTO(EstudianteEntity estudiante){
        setId_estudiante(estudiante.getId_estudiante());
        setId_persona(estudiante.getPersona().getId_persona());
        //setProfesor(estudiante.getProfesor());
        setComents(estudiante.getComents());
        setRama(estudiante.getRama());
        setNum_hours_week(estudiante.getNum_hours_week());
    }
}
