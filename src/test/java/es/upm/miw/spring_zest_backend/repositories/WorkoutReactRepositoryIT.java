package es.upm.miw.spring_zest_backend.repositories;

import es.upm.miw.spring_zest_backend.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

@TestConfig
class WorkoutReactRepositoryIT {

    @Autowired
    private WorkoutReactRepository workoutReactRepository;

    @Test
    void testFindAll() {
        StepVerifier
                .create(this.workoutReactRepository.findAll())
                .expectNextCount(4)
                .expectComplete()
                .verify();
    }
}