package es.upm.miw.spring_zest_backend.repositories;

import es.upm.miw.spring_zest_backend.documents.SessionExercise;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionExerciseRepository extends MongoRepository<SessionExercise, String> {
}
