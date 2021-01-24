package es.upm.miw.spring_zest_backend.repositories;

import es.upm.miw.spring_zest_backend.documents.Workout;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface WorkoutReactRepository extends ReactiveSortingRepository<Workout, String> {
}
