package es.upm.miw.spring_zest_backend.repositories;

import es.upm.miw.spring_zest_backend.TestConfig;
import es.upm.miw.spring_zest_backend.documents.SessionExercise;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class SessionExerciseRepositoryIT {

    @Autowired
    private SessionExerciseRepository sessionExerciseRepository;

    @Test
    void testFindAll() {
        List<SessionExercise> sessionExercises = this.sessionExerciseRepository.findAll();
        assertEquals(12, sessionExercises.size());
    }
}