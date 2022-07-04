package com.adri.ej31.persona.application;

import com.adri.ej31.exception.NotFoundException;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infrastructure.dto.input.PersonaInputDTO;
import com.adri.ej31.persona.infrastructure.dto.output.PersonaOutputDTO;
import com.adri.ej31.persona.infrastructure.repository.PersonaRepository;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReadPersonaUseCaseTest {

    private ReadPersonaUseCase underTest;
    @Mock
    private PersonaRepository personaRepo;

    @BeforeEach
    void setUp(){
        underTest = new ReadPersonaUseCase(personaRepo);
    }


    @Test
    void getPersonaById() {
        String id = "PER-0001";
        //when

        assertThatThrownBy(()-> underTest.getPersonaById(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("No se encuentra ninguna persona con id " + id);
        ArgumentCaptor<String> idArgCaptor = ArgumentCaptor.forClass(String.class);
        verify(personaRepo).findById(idArgCaptor.capture());
    }

    @Test
    void getPersonaByName_whenPersonaSaved_thenGetListWithPersona() {
        //given
        String name = "Test";
        //when
        underTest.getPersonaByName(name);
        ArgumentCaptor<String> nameArgCaptor = ArgumentCaptor.forClass(String.class);
        verify(personaRepo).findByName(nameArgCaptor.capture());
        assertThat(nameArgCaptor.getValue()).isEqualTo(name);

    }

    @Test
    void getPersonas_whenPersonasSaved_thenGetPersonas() {
        //when
        underTest.getPersonas();
        //then
        verify(personaRepo).findAll();
    }
}