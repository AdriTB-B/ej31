package com.adri.ej31.persona.application;

import com.adri.ej31.exception.DeleteNotAcceptedException;
import com.adri.ej31.exception.NotFoundException;
import com.adri.ej31.persona.application.port.DeletePersonaPort;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infraestructure.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePersonaUseCase implements DeletePersonaPort {
    @Autowired
    PersonaRepository repository;

    @Override
    public void deletePersona(String id) throws NotFoundException {
        PersonaEntity persona = repository.findById(id)
                .orElseThrow(()-> new NotFoundException("No se encuentra ninguna persona con id " + id));
        if(persona.getRolProfesor() != null) {
            throw new DeleteNotAcceptedException("Esta persona tiene un rol de profesor asignado. No se puede eliminar");
        }
        if(persona.getRolEstudiante() != null) {
            throw new DeleteNotAcceptedException("Esta persona tiene un rol de estudiante asignado. No se puede eliminar");
        }
        repository.delete(persona);
    }
}
