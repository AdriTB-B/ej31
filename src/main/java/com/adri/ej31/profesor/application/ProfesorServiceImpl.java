package com.adri.ej31.profesor.application;

import com.adri.ej31.estudiante.domain.EstudianteEntity;
import com.adri.ej31.estudiante.infrastructure.dto.output.EstudianteOutputDTO;
import com.adri.ej31.exception.NotFoundException;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infraestructure.repository.PersonaRepository;
import com.adri.ej31.profesor.application.port.ProfesorService;
import com.adri.ej31.profesor.domain.ProfesorEntity;
import com.adri.ej31.profesor.infrastructure.dto.input.ProfesorInputDTO;
import com.adri.ej31.profesor.infrastructure.dto.output.ProfesorOutputDTO;
import com.adri.ej31.profesor.infrastructure.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServiceImpl implements ProfesorService {
    @Autowired
    ProfesorRepository profesorRepo;
    @Autowired
    PersonaRepository personaRepo;

    @Override
    public ProfesorEntity findById(String id) {
        return profesorRepo.findById(id).get();
    }

    @Override
    public ProfesorOutputDTO save(ProfesorInputDTO profesorIn) {
        PersonaEntity persona = personaRepo.findById(profesorIn.getId_persona())
                .orElseThrow(()->new NotFoundException(
                        "No hay registrada ninguna persona con el id " + profesorIn.getId_persona()
                ));
        ProfesorEntity profesorEntity = new ProfesorEntity(profesorIn);
        profesorEntity.setPersona(persona);
        profesorRepo.save(profesorEntity);
        return new ProfesorOutputDTO(profesorEntity);
    }
}
