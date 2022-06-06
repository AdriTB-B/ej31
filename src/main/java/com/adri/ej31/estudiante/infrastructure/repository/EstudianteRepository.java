package com.adri.ej31.estudiante.infrastructure.repository;

import com.adri.ej31.estudiante.domain.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteEntity, String> {
}
