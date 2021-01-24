package es.upm.miw.spring_zest_backend.api_rest_controllers;

import es.upm.miw.spring_zest_backend.dtos.SessionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.spring_zest_backend.api_rest_controllers.SessionResource.SESSIONS;

@ApiTestConfig
public class SessionResourceIT {

    @Autowired
    private WebTestClient webTestClient;

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

}
