package es.upm.miw.spring_zest_backend.api_rest_controllers;

import es.upm.miw.spring_zest_backend.documents.SessionExercise;
import es.upm.miw.spring_zest_backend.dtos.SessionCreationDto;
import es.upm.miw.spring_zest_backend.dtos.SessionDto;
import es.upm.miw.spring_zest_backend.repositories.SessionExerciseRepository;
import es.upm.miw.spring_zest_backend.repositories.SessionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static es.upm.miw.spring_zest_backend.api_rest_controllers.SessionResource.ID;
import static es.upm.miw.spring_zest_backend.api_rest_controllers.SessionResource.SESSIONS;

@ApiTestConfig
public class SessionResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionExerciseRepository sessionExerciseRepository;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Test
    void testReadAllSessions() {
        this.webTestClient
                .get().uri(contextPath + SESSIONS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(SessionDto.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testCreateSession() {
        List<String> sessionExerciseIds = this.sessionExerciseRepository.findAll()
                .stream()
                .map(SessionExercise::getId)
                .collect(Collectors.toList());
        this.webTestClient
                .post().uri(contextPath + SESSIONS)
                .body(
                        BodyInserters.fromObject(
                                new SessionCreationDto("Stretching day A", new Date(), sessionExerciseIds)
                        )
                ).exchange().expectStatus().isOk().expectBody(SessionDto.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testUpdateSession() {
        String id = this.sessionRepository.findAll().get(0).getId();
        List<String> sessionExerciseIds = this.sessionExerciseRepository.findAll()
                .stream()
                .map(SessionExercise::getId)
                .collect(Collectors.toList());
        this.webTestClient
                .put().uri(contextPath + SESSIONS + ID, id)
                .body(
                        BodyInserters.fromObject(
                                new SessionCreationDto("Stretching day A", new Date(), sessionExerciseIds)
                        )
                ).exchange().expectStatus().isOk().expectBody(SessionDto.class)
                .value(Assertions::assertNotNull);
    }
}
