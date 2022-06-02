package com.adri.db1.infraestructure.repository;


import com.adri.db1.domain.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, String> {
    List<PersonaEntity> findByName(String nombre);
}
