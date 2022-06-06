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

@Service
public class EstudianteServiceImpl implements EstudianteService {
    @Autowired
    EstudianteRepository estudianteRepo;
    @Autowired
    PersonaRepository personaRepo;
    @Override
    public EstudianteEntity findEstudianteById(String id) {
        return estudianteRepo.findById(id).orElseThrow(() -> new NotFoundException(
                "No se encuentra ningun estudiante con id " + id
        ));
    }

    @Override
    public EstudianteOutputDTO save(EstudianteInputDTO estudiante) {
        PersonaEntity persona = personaRepo.findById(estudiante.getId_persona())
                .orElseThrow(()->new NotFoundException(
                        "No hay registrada ninguna persona con el id " + estudiante.getId_persona()
                ));
        EstudianteEntity estudianteEntity = new EstudianteEntity(estudiante);
        estudianteEntity.setPersona(persona);
        estudianteRepo.save(estudianteEntity);
        return new EstudianteOutputDTO(estudianteEntity);
    }

    @Override
    public void deleteById(String id) {
        estudianteRepo.deleteById(id);
    }

    @Override
    public EstudianteOutputDTO update(String id, EstudianteInputDTO estudianteIn) {
        EstudianteEntity estudianteToUpdate = findEstudianteById(id);
        System.out.println(estudianteToUpdate);
        PersonaEntity persona = personaRepo.findById(estudianteIn.getId_persona())
                .orElseThrow(()-> new NotFoundException("No existe ninguna persona con id " + id));
        estudianteToUpdate.update(estudianteIn);
        estudianteToUpdate.setPersona(persona);
        estudianteRepo.save(estudianteToUpdate);
        return new EstudianteOutputDTO(estudianteToUpdate);
    }
}
