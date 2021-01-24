package es.upm.miw.spring_zest_backend.business_controllers;

import es.upm.miw.spring_zest_backend.TestConfig;
import es.upm.miw.spring_zest_backend.dtos.WorkoutCreationDto;
import es.upm.miw.spring_zest_backend.repositories.WorkoutReactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

@TestConfig
class WorkoutControllerIT {

    @Autowired
    private WorkoutController workoutController;

    @Autowired
    private WorkoutReactRepository workoutReactRepository;

    @Test
    void testCreate() {
        WorkoutCreationDto workoutCreationDto = new WorkoutCreationDto("HIIT workout", "Full body", 420000L);
        StepVerifier
                .create(this.workoutController.createWorkout(workoutCreationDto))
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @Test
    void testReadAll() {
        StepVerifier
                .create(this.workoutController.readAllWorkouts())
                .expectNextCount(4)
                .expectComplete()
                .verify();
    }
}
