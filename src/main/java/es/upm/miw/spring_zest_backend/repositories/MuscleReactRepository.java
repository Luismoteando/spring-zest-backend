package es.upm.miw.spring_zest_backend.repositories;

import es.upm.miw.spring_zest_backend.documents.Muscle;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface MuscleReactRepository extends ReactiveSortingRepository<Muscle, String> {
}
