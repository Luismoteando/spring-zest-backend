package es.upm.miw.spring_zest_backend.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExerciseCreationDto {

    private String name;

    private String workoutId;

    private List<String> muscleIds;

    public ExerciseCreationDto() {
        // Empty for framework
    }

    public ExerciseCreationDto(String name, String workoutId, List<String> muscleIds) {
        this.name = name;
        this.workoutId = workoutId;
        this.muscleIds = muscleIds;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkoutId() {
        return this.workoutId;
    }

    public void setWorkoutId(String workoutId) {
        this.workoutId = workoutId;
    }

    public List<String> getMuscleIds() {
        return this.muscleIds;
    }

    public void setMuscleIds(List<String> muscleIds) {
        this.muscleIds = muscleIds;
    }
}
