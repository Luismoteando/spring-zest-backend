package es.upm.miw.spring_zest_backend.repositories;

import es.upm.miw.spring_zest_backend.documents.Session;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface SessionReactRepository extends ReactiveSortingRepository<Session, String> {
}
