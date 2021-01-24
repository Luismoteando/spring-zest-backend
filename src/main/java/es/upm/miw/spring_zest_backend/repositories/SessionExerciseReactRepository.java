package es.upm.miw.spring_zest_backend.repositories;

import es.upm.miw.spring_zest_backend.documents.SessionExercise;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface SessionExerciseReactRepository extends ReactiveSortingRepository<SessionExercise, String> {
}
