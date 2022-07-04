package com.adri.ej31.persona.infrastructure.repository;

import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infrastructure.dto.input.PersonaInputDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonaRepositoryTest {

    @Autowired
    PersonaRepository repo;

    @Test
    void findByName_whenPersonaSAved_thenGetListWithPersona() {
        String name = "Test name";
        PersonaEntity persona = new PersonaEntity();
        persona.setName(name);
        repo.save(persona);
        assertThat(repo.findByName(name)).isEqualTo(List.of(persona));

    }

    @Test
    void findByName_whenPersonaIsNotSaved_thenGetEmptyList() {
        String name = "Test name";
        assertThat(repo.findByName(name)).isEqualTo(List.of());

    }
}