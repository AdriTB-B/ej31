package com.adri.ej31.persona.application;

import com.adri.ej31.persona.infrastructure.repository.PersonaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

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
    @Disabled
    void getPersonaById() {
    }

    @Test
    @Disabled
    void getPersonaByName() {
    }

    @Test
    void getPersonas_whenPersonasSaved_thenGetPersonas() {
        //when
        underTest.getPersonas();
        //then
        verify(personaRepo).findAll();
    }
}