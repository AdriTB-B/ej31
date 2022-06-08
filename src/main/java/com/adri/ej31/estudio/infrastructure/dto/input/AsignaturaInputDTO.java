package com.adri.ej31.estudio.infrastructure.dto.input;

import com.adri.ej31.estudio.domain.AsignaturaEntity;
import com.adri.ej31.profesor.domain.ProfesorEntity;
import lombok.Data;

import java.util.Date;

@Data
public class AsignaturaInputDTO {
    private ProfesorEntity profesor;
    private String asignatura;
    private String comment;
    private Date initial_date;
    private Date finish_date;

    public AsignaturaInputDTO(AsignaturaEntity estudio){
        setProfesor(estudio.getProfesor());
        setAsignatura(estudio.getAsignatura());
        setComment(estudio.getComment());
        setInitial_date(estudio.getInitial_date());
        setFinish_date(estudio.getFinish_date());
    }
}
