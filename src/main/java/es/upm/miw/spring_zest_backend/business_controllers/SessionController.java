package es.upm.miw.spring_zest_backend.business_controllers;

import es.upm.miw.spring_zest_backend.documents.Session;
import es.upm.miw.spring_zest_backend.documents.SessionExercise;
import es.upm.miw.spring_zest_backend.dtos.SessionCreationDto;
import es.upm.miw.spring_zest_backend.dtos.SessionDto;
import es.upm.miw.spring_zest_backend.exceptions.BadRequestException;
import es.upm.miw.spring_zest_backend.exceptions.NotFoundException;
import es.upm.miw.spring_zest_backend.repositories.SessionExerciseReactRepository;
import es.upm.miw.spring_zest_backend.repositories.SessionReactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class SessionController {

    private static final String SESSION_EXERCISES_NOT_FOUND = "Session exercises: ";

    private SessionReactRepository sessionReactRepository;
    private SessionExerciseReactRepository sessionExerciseReactRepository;

    @Autowired
    public SessionController(
            SessionReactRepository sessionReactRepository,
            SessionExerciseReactRepository sessionExerciseReactRepository) {
        this.sessionReactRepository = sessionReactRepository;
        this.sessionExerciseReactRepository = sessionExerciseReactRepository;
    }

    public Mono<SessionDto> readSession(String id) {
        return this.sessionReactRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Session code (" + id + ")")))
                .map(SessionDto::new);
    }

    public Flux<SessionDto> readAllSessions() {
        return this.sessionReactRepository.findAll()
                .switchIfEmpty(Flux.error(new BadRequestException("Bad Request")))
                .map(SessionDto::new);
    }

    public Mono<SessionDto> createSession(SessionCreationDto sessionCreationDto) {
        Mono<Void> sessionExercises;
        Session session = Session.builder()
                .title(sessionCreationDto.getTitle())
                .start(sessionCreationDto.getStart())
                .build();
        if (sessionCreationDto.getSessionExerciseIds() == null) {
            sessionExercises = Mono.error(new BadRequestException("Session exercise list can't be null"));
        } else {
            sessionExercises = this.sessionExerciseReactRepository
                    .findAllById(sessionCreationDto.getSessionExerciseIds())
                    .switchIfEmpty(Mono.error(
                            new NotFoundException(SESSION_EXERCISES_NOT_FOUND + sessionCreationDto.getSessionExerciseIds())))
                    .collectList()
                    .doOnNext(session::setSessionExercises)
                    .then();
        }
        return Mono.when(sessionExercises)
                .then(this.sessionReactRepository.save(session).map(SessionDto::new));
    }

    public Mono<SessionDto> updateSession(String id, SessionCreationDto sessionCreationDto) {
        List<SessionExercise> sessionExercises = this.sessionExerciseReactRepository
                .findAllById(sessionCreationDto.getSessionExerciseIds())
                .switchIfEmpty(Mono.error(
                        new NotFoundException(SESSION_EXERCISES_NOT_FOUND + sessionCreationDto.getSessionExerciseIds())))
                .collectList()
                .block();

        Mono<Session> session = this.sessionReactRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Session id" + id)))
                .map(session1 -> {
                    session1.setTitle(sessionCreationDto.getTitle());
                    session1.setStart(sessionCreationDto.getStart());
                    session1.setSessionExercises(sessionExercises);
                    return session1;
                });

        return Mono.when(session)
                .then(this.sessionReactRepository.saveAll(session).next().map(SessionDto::new));
    }

    public Mono<Void> deleteSession(String id) {
        Mono<Session> session = this.sessionReactRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(("Session id" + id))));

        return Mono.when(session)
                .then(this.sessionReactRepository.deleteById(id));
    }
}
