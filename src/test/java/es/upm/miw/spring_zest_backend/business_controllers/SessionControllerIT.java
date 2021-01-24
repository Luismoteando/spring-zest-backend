package es.upm.miw.spring_zest_backend.business_controllers;

import es.upm.miw.spring_zest_backend.TestConfig;
import es.upm.miw.spring_zest_backend.documents.SessionExercise;
import es.upm.miw.spring_zest_backend.dtos.SessionCreationDto;
import es.upm.miw.spring_zest_backend.repositories.SessionExerciseRepository;
import es.upm.miw.spring_zest_backend.repositories.SessionReactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.util.Date;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
class SessionControllerIT {

    @Autowired
    private SessionController sessionController;

    @Autowired
    private SessionReactRepository sessionReactRepository;

    @Autowired
    private SessionExerciseRepository sessionExerciseRepository;

    @Test
    void testCreate() {
        SessionCreationDto sessionCreationDto =
                new SessionCreationDto("Speed Day 1", new Date(),
                        this.sessionExerciseRepository.findAll().stream()
                                .map(SessionExercise::getId)
                                .collect(Collectors.toList())
                );

        StepVerifier
                .create(this.sessionController.createSession(sessionCreationDto))
                .expectNextMatches(session -> {
                    assertEquals("Speed Day 1", session.getTitle());
                    assertNotNull(session.getSessionExerciseIds());
                    return true;
                })
                .expectComplete()
                .verify();
    }

    @Test
    void testReadAll() {
        StepVerifier
                .create(this.sessionController.readAllSessions())
                .expectNextCount(4)
                .expectComplete()
                .verify();
    }
}
