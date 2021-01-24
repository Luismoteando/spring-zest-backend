package es.upm.miw.spring_zest_backend.api_rest_controllers;

import es.upm.miw.spring_zest_backend.business_controllers.SessionController;
import es.upm.miw.spring_zest_backend.dtos.SessionCreationDto;
import es.upm.miw.spring_zest_backend.dtos.SessionDto;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(SessionResource.SESSIONS)
public class SessionResource {

    public static final String SESSIONS = "/sessions";
    public static final String ID = "/{id}";

    private SessionController sessionController;

    @Autowired
    public SessionResource(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    @GetMapping(value = ID)
    public Mono<SessionDto> read(@PathVariable String id) {
        return this.sessionController.readSession(id)
                .doOnNext(log -> LogManager.getLogger(this.getClass()).debug(log));
    }

    @GetMapping
    public Flux<SessionDto> readAll() {
        return this.sessionController.readAllSessions()
                .doOnNext(log -> LogManager.getLogger(this.getClass()).debug(log));
    }

    @PostMapping
    public Mono<SessionDto> createSession(@Valid @RequestBody SessionCreationDto sessionCreationDto) {
        return this.sessionController.createSession(sessionCreationDto)
                .doOnNext(log -> LogManager.getLogger(this.getClass()).debug(log));
    }

    @PutMapping(value = ID)
    public Mono<SessionDto> updateSession(@PathVariable String id, @RequestBody SessionCreationDto sessionDto) {
        return this.sessionController.updateSession(id, sessionDto)
                .doOnNext(log -> LogManager.getLogger(this.getClass()).debug(log));
    }
}
