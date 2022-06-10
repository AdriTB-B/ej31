package com.adri.ej31.estudiante_asignatura.infrastructure.repository;

import com.adri.ej31.estudiante_asignatura.domain.EstudianteAsignaturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteAsignaturaRepository extends JpaRepository<EstudianteAsignaturaEntity, String> {
}
