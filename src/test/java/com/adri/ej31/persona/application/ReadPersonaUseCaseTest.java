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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void getPersonaById_whenPersonaExist_thenReturnPersona() {
        String id = "PER-0001";
        //when
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setId_persona(id);
        when(personaRepo.findById(id)).thenReturn(Optional.of(personaEntity));
        assertEquals(underTest.getPersonaById(id), personaEntity);
    }
    @Test
    void getPersonaById_whenPersonaNotExist_thenThrowNotFoundException() {
        String id = "PER-0001";
        //when
        when(personaRepo.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(()-> underTest.getPersonaById(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("No se encuentra ninguna persona con id " + id);
        //verify(personaRepo).findById(id);
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