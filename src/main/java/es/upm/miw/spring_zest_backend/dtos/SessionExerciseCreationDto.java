package es.upm.miw.spring_zest_backend.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionExerciseCreationDto {

    private Integer order;

    private String repetitions;

    private Double weight;

    private Integer sets;

    private String exerciseId;

    public SessionExerciseCreationDto() {
        // Empty for framework
    }

    public SessionExerciseCreationDto(Integer order, String repetitions, Double weight, Integer sets, String exerciseId) {
        this.order = order;
        this.repetitions = repetitions;
        this.weight = weight;
        this.sets = sets;
        this.exerciseId = exerciseId;
    }

    public Integer getOrder() {
        return this.order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getRepetitions() {
        return this.repetitions;
    }

    public void setRepetitions(String repetitions) {
        this.repetitions = repetitions;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }
}
