package es.upm.miw.spring_zest_backend.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.upm.miw.spring_zest_backend.documents.Exercise;
import es.upm.miw.spring_zest_backend.documents.Muscle;

import java.util.List;
import java.util.stream.Collectors;

public class ExerciseDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    private String name;

    private String workoutId;

    private List<String> muscleIds;

    public ExerciseDto() {
        // Empty for framework
    }

    public ExerciseDto(String name, String workoutId, List<String> muscleIds) {
        this.name = name;
        this.workoutId = workoutId;
        this.muscleIds = muscleIds;
    }

    public ExerciseDto(Exercise exercise) {
        this(
                exercise.getName(),
                exercise.getWorkout().getId(),
                exercise.getMuscles().stream()
                        .map(Muscle::getId)
                        .collect(Collectors.toList())
        );
    }

    public String getName() {
        return this.name;
    }

    public String getWorkoutId() {
        return this.workoutId;
    }

    public List<String> getMuscleIds() {
        return this.muscleIds;
    }

    @Override
    public String toString() {
        return "ExerciseDto { " +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", workoutId='" + this.workoutId + '\'' +
                ", muscleIds='" + this.muscleIds + '\'' +
                " }";
    }
}
