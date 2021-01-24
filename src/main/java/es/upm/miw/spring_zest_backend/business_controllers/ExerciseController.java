package es.upm.miw.spring_zest_backend.business_controllers;

import es.upm.miw.spring_zest_backend.documents.Exercise;
import es.upm.miw.spring_zest_backend.dtos.ExerciseCreationDto;
import es.upm.miw.spring_zest_backend.dtos.ExerciseDto;
import es.upm.miw.spring_zest_backend.exceptions.BadRequestException;
import es.upm.miw.spring_zest_backend.exceptions.NotFoundException;
import es.upm.miw.spring_zest_backend.repositories.ExerciseReactRepository;
import es.upm.miw.spring_zest_backend.repositories.MuscleReactRepository;
import es.upm.miw.spring_zest_backend.repositories.WorkoutReactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class ExerciseController {

    private static final String WORKOUT_NOT_FOUND = "Workout: ";
    private static final Object MUSCLES_NOT_FOUND = "Muscle: ";

    private ExerciseReactRepository exerciseReactRepository;
    private WorkoutReactRepository workoutReactRepository;
    private MuscleReactRepository muscleReactRepository;

    @Autowired
    public ExerciseController(
            ExerciseReactRepository exerciseReactRepository,
            WorkoutReactRepository sessionExerciseReactRepository,
            MuscleReactRepository muscleReactRepository
    ) {
        this.exerciseReactRepository = exerciseReactRepository;
        this.workoutReactRepository = sessionExerciseReactRepository;
        this.muscleReactRepository = muscleReactRepository;
    }

    Flux<ExerciseDto> readAllExercises() {
        return this.exerciseReactRepository.findAll()
                .switchIfEmpty(Flux.error(new BadRequestException("Bad Request")))
                .map(ExerciseDto::new);
    }

    Mono<ExerciseDto> createExercise(
            ExerciseCreationDto exerciseCreationDto) {
        Mono<Void> workout;
        Mono<Void> muscles;
        Exercise exercise = Exercise.builder()
                .name(exerciseCreationDto.getName())
                .build();
        if (exerciseCreationDto.getWorkoutId() == null) {
            workout = Mono.error(new BadRequestException("Workout can't be null"));
        } else {
            workout = this.workoutReactRepository
                    .findById(exerciseCreationDto.getWorkoutId())
                    .switchIfEmpty(Mono.error(
                            new NotFoundException(WORKOUT_NOT_FOUND + exerciseCreationDto.getWorkoutId())))
                    .doOnNext(exercise::setWorkout)
                    .then();
        }
        if (exerciseCreationDto.getMuscleIds() == null) {
            muscles = Mono.error(new BadRequestException("Muscle list can't be null"));
        } else {
            muscles = this.muscleReactRepository
                    .findAllById(exerciseCreationDto.getMuscleIds())
                    .switchIfEmpty(Mono.error(
                            new NotFoundException(MUSCLES_NOT_FOUND + exerciseCreationDto.getMuscleIds().toString())))
                    .collectList()
                    .doOnNext(exercise::setMuscles)
                    .then();
        }
        return Mono.when(workout, muscles)
                .then(this.exerciseReactRepository
                        .save(exercise).map(ExerciseDto::new));
    }
}
