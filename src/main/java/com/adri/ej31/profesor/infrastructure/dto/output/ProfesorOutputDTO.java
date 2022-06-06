package com.adri.ej31.profesor.infrastructure.dto.output;

import com.adri.ej31.profesor.domain.ProfesorEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfesorOutputDTO {
    private String id_profesor;
    private String id_persona;
    private String coments;
    private String rama;

    public ProfesorOutputDTO(ProfesorEntity profesor){
        setId_profesor(profesor.getId_profesor());
        setComents(profesor.getComents());
        setRama(profesor.getRama());
        setId_persona(profesor.getPersona().getId_persona());
    }
}
