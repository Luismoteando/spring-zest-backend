package es.upm.miw.spring_zest_backend.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkoutCreationDto {

    private String name;

    private String goal;

    private Long duration;

    public WorkoutCreationDto() {
        // Empty for framework
    }

    public WorkoutCreationDto(String name, String goal, Long duration) {
        this.name = name;
        this.goal = goal;
        this.duration = duration;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoal() {
        return this.goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Long getDuration() {
        return this.duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
