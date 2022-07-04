package com.adri.ej31.persona.application;

import com.adri.ej31.persona.application.port.CreatePersonaPort;
import com.adri.ej31.persona.domain.PersonaEntity;
import com.adri.ej31.persona.infrastructure.dto.input.PersonaInputDTO;
import com.adri.ej31.persona.infrastructure.dto.output.PersonaOutputDTO;
import com.adri.ej31.persona.infrastructure.repository.PersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreatePersonaUseCaseTest {

    @Mock
    PersonaRepository personaRepo;
    CreatePersonaUseCase underTest;

    @BeforeEach
    void setUp() {
        underTest = new CreatePersonaUseCase(personaRepo);
    }

    @Test
    void addPersona_whenSavePersona_thenGetPersona() {
        //given
        PersonaInputDTO persona = PersonaInputDTO
                .builder()
                .name("Name").city("city")
                .company_email("test@test.com")
                .personal_email("test1@test.com")
                .password("pass")
                .usuario("test")
                .surname("Surname")
                .build();
        //when
        PersonaOutputDTO personaOut = underTest.addPersona(persona);
        //then
        ArgumentCaptor<PersonaEntity> personaArgCaptor = ArgumentCaptor.forClass(PersonaEntity.class);
        verify(personaRepo).save(personaArgCaptor.capture());
        PersonaEntity capturedPersona = personaArgCaptor.getValue();
        assertThat(capturedPersona.getId_persona()).isEqualTo(personaOut.getId_persona());


    }
}