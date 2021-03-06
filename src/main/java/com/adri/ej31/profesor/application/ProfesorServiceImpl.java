package com.adri.ej31.profesor.application;

import com.adri.ej31.estudiante.infrastructure.dto.output.EstudianteOutputDTO;
import com.adri.ej31.exception.IncorrectRolException;
import com.adri.ej31.exception.NotFoundException;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infrastructure.repository.PersonaRepository;
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
    public ProfesorOutputDTO findProfesorById(String id) {
        ProfesorEntity profesor = profesorRepo.findById(id)
                .orElseThrow(()-> new NotFoundException(
                        "No hay registrado ningún profesor con el id " + id
                ));
        profesor.getEstudiantes().forEach(e-> System.out.println(new EstudianteOutputDTO(e)));
        return new ProfesorOutputDTO(profesor);
    }

    @Override
    public ProfesorOutputDTO save(ProfesorInputDTO profesorIn) {
        PersonaEntity persona = personaRepo.findById(profesorIn.getId_persona())
                .orElseThrow(()->new NotFoundException(
                        "No hay registrada ninguna persona con el id " + profesorIn.getId_persona()
                ));
        checkRolAssigment(persona);
        ProfesorEntity profesorEntity = new ProfesorEntity(profesorIn);
        profesorEntity.setPersona(persona);
        profesorRepo.save(profesorEntity);
        return new ProfesorOutputDTO(profesorEntity);
    }

    @Override
    public ProfesorOutputDTO updateProfesor(String id, ProfesorInputDTO profesorIn) {
        ProfesorEntity profesorToUpdate = profesorRepo.findById(id)
                .orElseThrow(()-> new NotFoundException("No existe ningun profesor con id " + id));
        PersonaEntity persona = personaRepo.findById(profesorIn.getId_persona())
                .orElseThrow(()-> new NotFoundException("No existe ninguna persona con id " + id));
        checkRolAssigment(persona);
        profesorToUpdate.update(profesorIn);
        profesorToUpdate.setPersona(persona);
        profesorRepo.save(profesorToUpdate);
        return new ProfesorOutputDTO(profesorToUpdate);
    }

    @Override
    public void deleteProfesor(String id) {
        profesorRepo.deleteById(id);
    }

    private void checkRolAssigment(PersonaEntity persona){
        if(persona.getRolEstudiante() != null) {
            throw new IncorrectRolException("Esta persona ya esta asginada como estudiante");
        }
    }
}
