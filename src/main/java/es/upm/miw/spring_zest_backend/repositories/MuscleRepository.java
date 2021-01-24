package es.upm.miw.spring_zest_backend.repositories;

import es.upm.miw.spring_zest_backend.documents.Muscle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MuscleRepository extends MongoRepository<Muscle, String> {
}
