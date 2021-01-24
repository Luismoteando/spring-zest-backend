package es.upm.miw.spring_zest_backend.repositories;

import es.upm.miw.spring_zest_backend.documents.MuscleGroup;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface MuscleGroupReactRepository extends ReactiveSortingRepository<MuscleGroup, String> {
}
