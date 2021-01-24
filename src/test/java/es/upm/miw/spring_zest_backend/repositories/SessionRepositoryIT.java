package es.upm.miw.spring_zest_backend.repositories;

import es.upm.miw.spring_zest_backend.TestConfig;
import es.upm.miw.spring_zest_backend.documents.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class SessionRepositoryIT {

    @Autowired
    private SessionRepository sessionRepository;

    @Test
    void testFindAll() {
        List<Session> sessions = this.sessionRepository.findAll();
        assertEquals(4, sessions.size());
    }
}