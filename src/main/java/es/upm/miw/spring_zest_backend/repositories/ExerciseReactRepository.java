package es.upm.miw.spring_zest_backend.repositories;

import es.upm.miw.spring_zest_backend.documents.Exercise;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface ExerciseReactRepository extends ReactiveSortingRepository<Exercise, String> {
}
