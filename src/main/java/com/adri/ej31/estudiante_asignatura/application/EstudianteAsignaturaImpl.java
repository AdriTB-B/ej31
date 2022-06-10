package com.adri.ej31.estudiante_asignatura.application;

import com.adri.ej31.estudiante.domain.EstudianteEntity;
import com.adri.ej31.estudiante.infrastructure.dto.output.EstudianteOutputDTO;
import com.adri.ej31.estudiante.infrastructure.repository.EstudianteRepository;
import com.adri.ej31.estudiante_asignatura.application.port.EstudianteAsignaturaService;
import com.adri.ej31.estudiante_asignatura.domain.EstudianteAsignaturaEntity;
import com.adri.ej31.estudiante_asignatura.infrastructure.dto.input.EstudianteAsignaturaInputDTO;
import com.adri.ej31.estudiante_asignatura.infrastructure.dto.output.EstudianteAsignaturaOutputDTO;
import com.adri.ej31.estudiante_asignatura.infrastructure.repository.EstudianteAsignaturaRepository;
import com.adri.ej31.exception.NotFoundException;
import com.adri.ej31.profesor.domain.ProfesorEntity;
import com.adri.ej31.profesor.infrastructure.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteAsignaturaImpl implements EstudianteAsignaturaService {
    @Autowired
    EstudianteAsignaturaRepository estAsiRepo;
    @Autowired
    ProfesorRepository profesorRepo;
    @Autowired
    EstudianteRepository estudianteRepo;

    @Override
    public EstudianteAsignaturaOutputDTO save(EstudianteAsignaturaInputDTO estAsiIn) {
        EstudianteAsignaturaEntity estudianteAsignatura = new EstudianteAsignaturaEntity(estAsiIn);
        if(estAsiIn.getId_profesor() != null){
            estudianteAsignatura.setProfesor(findProfesor(estAsiIn.getId_profesor()));
        }
        List<EstudianteEntity> estudiantesToInsert = findEstudiantesByIds(estAsiIn.getIds_estudiantes());
        estudianteAsignatura.setEstudiantes(estudiantesToInsert);
        estAsiRepo.save(estudianteAsignatura);
        return new EstudianteAsignaturaOutputDTO(estudianteAsignatura);
    }

    @Override
    public EstudianteAsignaturaOutputDTO update(String id, EstudianteAsignaturaInputDTO asignaturaIn) {
        EstudianteAsignaturaEntity estudianteAsignatura = estAsiRepo.findById(id)
                .orElseThrow(()-> new NotFoundException(
                        "No se encuentra ninguna asignatura con el id " + id
                ));
        estudianteAsignatura.update(asignaturaIn);
        if (asignaturaIn.getId_profesor() != null){
            estudianteAsignatura.setProfesor(findProfesor(asignaturaIn.getId_profesor()));
        }
        List<String> idsEstudiantes = new ArrayList<>(estudianteAsignatura.getEstudiantes().stream()
                .map(EstudianteEntity::getId_estudiante).toList());
        idsEstudiantes.addAll(asignaturaIn.getIds_estudiantes());
        //No persiste los estudiantes que se repitan
        estudianteAsignatura.setEstudiantes(findEstudiantesByIds(idsEstudiantes.stream().distinct().toList()));
        estAsiRepo.save(estudianteAsignatura);
        return new EstudianteAsignaturaOutputDTO(estudianteAsignatura);
    }

    @Override
    public EstudianteAsignaturaOutputDTO findById(String id) {
        return new EstudianteAsignaturaOutputDTO(estAsiRepo.findById(id)
                .orElseThrow(()-> new NotFoundException(
                        "No se encuentra ninguna asignatura con el id " + id
                ))
        );
    }

    @Override
    public void deleteById(String id) {
        estAsiRepo.deleteById(id);
    }

    @Override
    public List<EstudianteAsignaturaOutputDTO> getAsignaturasByIdEstudiante(String id_estudiante) {
        EstudianteEntity estudiante = estudianteRepo.findById(id_estudiante)
                .orElseThrow(()-> new NotFoundException(
                        "No hay ningún estudiante con el id " + id_estudiante
                ));
        return estudiante.getAsignaturas().stream().map(EstudianteAsignaturaOutputDTO::new).collect(Collectors.toList());
    }

    private ProfesorEntity findProfesor(String id){
        ProfesorEntity profesor = profesorRepo.findById(id)
                .orElseThrow(()-> new NotFoundException(
                        "No se ha encontrado ningún profesor con el id " +id
                ));
        return profesor;
    }
    private List<EstudianteEntity> findEstudiantesByIds(List<String> ids){
        List<EstudianteEntity> estudiantes = new ArrayList<>();
        if(ids != null){
            estudiantes = estudianteRepo.findAllById(ids);
            if (ids.size() != estudiantes.size()){
                throw new NotFoundException("No se han encontrado uno o varios de los estudiantes con ids " + ids);
            }
        }
        return estudiantes;
    }
}
