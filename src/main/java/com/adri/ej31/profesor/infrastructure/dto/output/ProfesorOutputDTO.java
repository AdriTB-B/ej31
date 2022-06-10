package com.adri.ej31.profesor.infrastructure.dto.output;

import com.adri.ej31.estudiante.domain.EstudianteEntity;
import com.adri.ej31.profesor.domain.ProfesorEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProfesorOutputDTO {
    private String id_profesor;
    private String id_persona;
    private String coments;
    private String rama;
    private List<String> ids_estudiantes;

    public ProfesorOutputDTO(ProfesorEntity profesor){
        setId_profesor(profesor.getId_profesor());
        setComents(profesor.getComents());
        setRama(profesor.getRama());
        setId_persona(profesor.getPersona().getId_persona());
        if(profesor.getEstudiantes() != null){
            setIds_estudiantes(profesor.getEstudiantes().stream()
                    .map(EstudianteEntity::getId_estudiante)
                    .collect(Collectors.toList())
            );
        }
    }
}
