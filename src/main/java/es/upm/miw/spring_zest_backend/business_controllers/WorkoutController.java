package es.upm.miw.spring_zest_backend.business_controllers;

import es.upm.miw.spring_zest_backend.documents.Workout;
import es.upm.miw.spring_zest_backend.dtos.WorkoutCreationDto;
import es.upm.miw.spring_zest_backend.dtos.WorkoutDto;
import es.upm.miw.spring_zest_backend.exceptions.BadRequestException;
import es.upm.miw.spring_zest_backend.repositories.WorkoutReactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class WorkoutController {

    private WorkoutReactRepository workoutReactRepository;

    @Autowired
    public WorkoutController(WorkoutReactRepository workoutReactRepository) {
        this.workoutReactRepository = workoutReactRepository;
    }

    Flux<WorkoutDto> readAllWorkouts() {
        return this.workoutReactRepository.findAll()
                .switchIfEmpty(Flux.error(new BadRequestException("Bad Request")))
                .map(WorkoutDto::new);
    }

    Mono<WorkoutDto> createWorkout(WorkoutCreationDto workoutCreationDto) {
        Workout workout = Workout.builder()
                .name(workoutCreationDto.getName())
                .goal(workoutCreationDto.getGoal())
                .duration(workoutCreationDto.getDuration())
                .build();
        return this.workoutReactRepository.save(workout)
                .map(WorkoutDto::new);
    }
}
