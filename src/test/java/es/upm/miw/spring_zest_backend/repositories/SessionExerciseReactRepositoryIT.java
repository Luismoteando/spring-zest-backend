package es.upm.miw.spring_zest_backend.repositories;

import es.upm.miw.spring_zest_backend.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

@TestConfig
class SessionExerciseReactRepositoryIT {

    @Autowired
    private SessionExerciseReactRepository sessionExerciseReactRepository;

    @Test
    void testFindAll() {
        StepVerifier
                .create(this.sessionExerciseReactRepository.findAll())
                .expectNextCount(12)
                .expectComplete()
                .verify();
    }
}