package com.adri.ej31.estudiante.application;

import com.adri.ej31.estudiante.application.port.EstudianteService;
import com.adri.ej31.estudiante.domain.EstudianteEntity;
import com.adri.ej31.estudiante.infrastructure.dto.input.EstudianteInputDTO;
import com.adri.ej31.estudiante.infrastructure.dto.output.EstudianteOutputDTO;
import com.adri.ej31.estudiante.infrastructure.repository.EstudianteRepository;
import com.adri.ej31.estudiante_asignatura.domain.EstudianteAsignaturaEntity;
import com.adri.ej31.estudiante_asignatura.infrastructure.repository.EstudianteAsignaturaRepository;
import com.adri.ej31.exception.IncorrectRolException;
import com.adri.ej31.exception.NotFoundException;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infrastructure.repository.PersonaRepository;
import com.adri.ej31.profesor.domain.ProfesorEntity;
import com.adri.ej31.profesor.infrastructure.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteServiceImpl implements EstudianteService {
    @Autowired
    EstudianteRepository estudianteRepo;
    @Autowired
    PersonaRepository personaRepo;
    @Autowired
    ProfesorRepository profesorRepo;
    @Autowired
    EstudianteAsignaturaRepository estAsiRepo;

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
        checkRolAssigment(persona);

        ProfesorEntity profesor = null;
        if(estudiante.getId_profesor() != null){
             profesor = profesorRepo.findById(estudiante.getId_profesor())
                    .orElseThrow(()->new NotFoundException(
                            "No hay registrada ning??n profesor con el id " + estudiante.getId_profesor()
                    ));
        }

        List<EstudianteAsignaturaEntity> asignaturas = findAsignaturasByIds(estudiante.getIds_asignaturas());

        EstudianteEntity estudianteEntity = new EstudianteEntity(estudiante);
        estudianteEntity.setPersona(persona);
        estudianteEntity.setProfesor(profesor);
        estudianteEntity.setAsignaturas(asignaturas);
        asignaturas.forEach(asignatura -> asignatura.addEstudiante(estudianteEntity));
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
        PersonaEntity persona = personaRepo.findById(estudianteIn.getId_persona())
                .orElseThrow(()-> new NotFoundException("No existe ninguna persona con id " + id));
        checkRolAssigment(persona);
        estudianteToUpdate.update(estudianteIn);
        estudianteToUpdate.setPersona(persona);
        estudianteRepo.save(estudianteToUpdate);
        return new EstudianteOutputDTO(estudianteToUpdate);
    }

    @Override
    public EstudianteOutputDTO addAsignaturas(String id_estudiante, List<String> ids_asignaturasToInsert) {
        EstudianteEntity estudiante = this.findEstudianteById(id_estudiante);
        List<String> idsAsignaturas = estudiante.getAsignaturas()
                .stream()
                .map(EstudianteAsignaturaEntity::getId_asignatura)
                .collect(Collectors.toList());
        idsAsignaturas.addAll(ids_asignaturasToInsert);
        List<EstudianteAsignaturaEntity> asignaturas = findAsignaturasByIds(idsAsignaturas
                .stream()
                .distinct()
                .collect(Collectors.toList())
        );
        estudiante.setAsignaturas(asignaturas);
        asignaturas.forEach(asignatura -> asignatura.addEstudiante(estudiante));
        estudianteRepo.save(estudiante);
        return new EstudianteOutputDTO(estudiante);
    }

    @Override
    public EstudianteOutputDTO removeAsignaturas(String id_estudiante, List<String> ids_asignaturas) {
        EstudianteEntity estudiante = this.findEstudianteById(id_estudiante);
        List<EstudianteAsignaturaEntity> asignaturas = findAsignaturasByIds(ids_asignaturas);
        estudiante.getAsignaturas().removeAll(asignaturas);
        asignaturas.forEach(asignatura -> asignatura.removeEstudiante(estudiante));
        estudianteRepo.save(estudiante);
        return new EstudianteOutputDTO(estudiante);
    }

    private List<EstudianteAsignaturaEntity> findAsignaturasByIds(List<String> ids) {
        List<EstudianteAsignaturaEntity> asignaturas = new ArrayList<>();
        if(ids != null){
            asignaturas = estAsiRepo.findAllById(ids);
            if (ids.size() != asignaturas.size()){
                throw new NotFoundException("No se han encontrado una o varias de las asignaturas con ids " + ids);
            }
        }
        return asignaturas;
    }

    private void checkRolAssigment(PersonaEntity persona){
        if(persona.getRolProfesor() != null) {
            throw new IncorrectRolException("Esta persona ya esta asginada como profesor");
        }
    }
}
