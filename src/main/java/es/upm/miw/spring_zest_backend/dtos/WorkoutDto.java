package es.upm.miw.spring_zest_backend.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.upm.miw.spring_zest_backend.documents.Workout;

public class WorkoutDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    private String name;

    private String goal;

    private Long duration;

    public WorkoutDto() {
        // Empty for framework
    }

    public WorkoutDto(String name, String goal, Long duration) {
        this.name = name;
        this.goal = goal;
        this.duration = duration;
    }

    public WorkoutDto(Workout workout) {
        this(
                workout.getName(),
                workout.getGoal(),
                workout.getDuration()
        );
    }

    public String getName() {
        return this.name;
    }

    public String getGoal() {
        return this.goal;
    }

    public Long getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return "WorkoutDto { " +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", goal='" + this.goal + '\'' +
                ", duration='" + this.duration + '\'' +
                " }";
    }
}
