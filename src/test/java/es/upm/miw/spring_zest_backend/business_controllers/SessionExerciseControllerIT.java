package es.upm.miw.spring_zest_backend.business_controllers;

import es.upm.miw.spring_zest_backend.TestConfig;
import es.upm.miw.spring_zest_backend.dtos.SessionExerciseCreationDto;
import es.upm.miw.spring_zest_backend.repositories.ExerciseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
class SessionExerciseControllerIT {

    @Autowired
    private SessionExerciseController sessionExerciseController;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Test
    void testCreate() {
        SessionExerciseCreationDto sessionExerciseCreationDto =
                new SessionExerciseCreationDto(
                        1,
                        "6-12",
                        45.0,
                        3,
                        this.exerciseRepository.findAll().get(1).getId());

        StepVerifier
                .create(this.sessionExerciseController
                        .createWorkoutSessionExercise(sessionExerciseCreationDto))
                .expectNextMatches(sessionExercise -> {
                    assertEquals("6-12", sessionExercise.getRepetitions());
                    assertNotNull(sessionExercise.getExerciseId());
                    return true;
                })
                .expectComplete()
                .verify();
    }

    @Test
    void testReadAll() {
        StepVerifier
                .create(this.sessionExerciseController.readAllSessionExercises())
                .expectNextCount(12)
                .expectComplete()
                .verify();
    }
}
