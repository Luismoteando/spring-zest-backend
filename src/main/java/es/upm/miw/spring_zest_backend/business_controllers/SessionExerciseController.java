package es.upm.miw.spring_zest_backend.business_controllers;

import es.upm.miw.spring_zest_backend.documents.SessionExercise;
import es.upm.miw.spring_zest_backend.dtos.SessionExerciseCreationDto;
import es.upm.miw.spring_zest_backend.dtos.SessionExerciseDto;
import es.upm.miw.spring_zest_backend.exceptions.BadRequestException;
import es.upm.miw.spring_zest_backend.exceptions.NotFoundException;
import es.upm.miw.spring_zest_backend.repositories.ExerciseReactRepository;
import es.upm.miw.spring_zest_backend.repositories.SessionExerciseReactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class SessionExerciseController {

    private static final String EXERCISE_NOT_FOUND = "Exercise: ";

    private SessionExerciseReactRepository sessionExerciseReactRepository;
    private ExerciseReactRepository exerciseReactRepository;

    @Autowired
    public SessionExerciseController(
            SessionExerciseReactRepository sessionExerciseReactRepository,
            ExerciseReactRepository exerciseReactRepository) {
        this.sessionExerciseReactRepository = sessionExerciseReactRepository;
        this.exerciseReactRepository = exerciseReactRepository;
    }

    Flux<SessionExerciseDto> readAllSessionExercises() {
        return this.sessionExerciseReactRepository.findAll()
                .switchIfEmpty(Flux.error(new BadRequestException("Bad Request")))
                .map(SessionExerciseDto::new);
    }

    Mono<SessionExerciseDto> createWorkoutSessionExercise(
            SessionExerciseCreationDto sessionExerciseCreationDto) {
        Mono<Void> exercise;
        SessionExercise sessionExercise = SessionExercise.builder()
                .order(sessionExerciseCreationDto.getOrder())
                .repetitions(sessionExerciseCreationDto.getRepetitions())
                .weight(sessionExerciseCreationDto.getWeight())
                .sets(sessionExerciseCreationDto.getSets())
                .build();
        if (sessionExerciseCreationDto.getExerciseId() == null) {
            exercise = Mono.error(new BadRequestException("Exercise can't be null"));
        } else {
            exercise = this.exerciseReactRepository
                    .findById(sessionExerciseCreationDto.getExerciseId())
                    .switchIfEmpty(Mono.error(
                            new NotFoundException(EXERCISE_NOT_FOUND + sessionExerciseCreationDto.getExerciseId())))
                    .doOnNext(sessionExercise::setExercise)
                    .then();
        }
        return Mono.when(exercise)
                .then(this.sessionExerciseReactRepository
                        .save(sessionExercise).map(SessionExerciseDto::new));
    }
}
