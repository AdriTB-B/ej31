package com.adri.db1;


import com.adri.db1.model.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPersonaRepository extends JpaRepository<PersonaEntity, Integer> {
}
