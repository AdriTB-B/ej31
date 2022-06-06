package com.adri.ej31.estudiante.application;

import com.adri.ej31.estudiante.application.port.EstudianteService;
import com.adri.ej31.estudiante.domain.EstudianteEntity;
import com.adri.ej31.estudiante.infrastructure.dto.input.EstudianteInputDTO;
import com.adri.ej31.estudiante.infrastructure.dto.output.EstudianteOutputDTO;
import com.adri.ej31.estudiante.infrastructure.repository.EstudianteRepository;
import com.adri.ej31.exception.NotFoundException;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infraestructure.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {
    @Autowired
    EstudianteRepository estudianteRepo;
    @Autowired
    PersonaRepository personaRepo;
    @Override
    public EstudianteEntity findById(String id) {
        return estudianteRepo.findById(id).get();
    }

    @Override
    public EstudianteOutputDTO save(EstudianteInputDTO estudiante) {
        Optional<PersonaEntity> oPpersona = personaRepo.findById(estudiante.getId_persona());
        EstudianteEntity estudianteEntity = null;
        if(oPpersona.isPresent()){
            estudianteEntity = new EstudianteEntity(estudiante);
            estudianteEntity.setPersona(oPpersona.get());
            estudianteRepo.save(estudianteEntity);
        }else {
            throw new NotFoundException("No hay registros de persona con id " + estudiante.getId_persona());
        }
        return new EstudianteOutputDTO(estudianteEntity);
    }

    @Override
    public void deleteById(String id) {
        estudianteRepo.deleteById(id);
    }
}
