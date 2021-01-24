package es.upm.miw.spring_zest_backend.repositories;

import es.upm.miw.spring_zest_backend.documents.MuscleGroup;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MuscleGroupRepository extends MongoRepository<MuscleGroup, String> {
}
