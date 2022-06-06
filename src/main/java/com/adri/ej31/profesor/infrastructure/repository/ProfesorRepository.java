package com.adri.ej31.profesor.infrastructure.repository;

import com.adri.ej31.profesor.domain.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<ProfesorEntity, String> {
}
