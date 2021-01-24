package es.upm.miw.spring_zest_backend.repositories;

import es.upm.miw.spring_zest_backend.documents.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExerciseRepository extends MongoRepository<Exercise, String> {
}
