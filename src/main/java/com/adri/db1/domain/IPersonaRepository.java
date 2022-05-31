package com.adri.db1.domain;


import com.adri.db1.domain.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IPersonaRepository extends JpaRepository<PersonaEntity, Integer> {
    List<PersonaEntity> findByName(String nombre);
    void deleteById(Integer id);
}
