package es.upm.miw.spring_zest_backend.repositories;

import es.upm.miw.spring_zest_backend.TestConfig;
import es.upm.miw.spring_zest_backend.documents.Workout;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class WorkoutRepositoryIT {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Test
    void testFindAll() {
        List<Workout> workouts = this.workoutRepository.findAll();
        assertEquals(4, workouts.size());
    }
}