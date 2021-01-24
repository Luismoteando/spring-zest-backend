package es.upm.miw.spring_zest_backend.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.upm.miw.spring_zest_backend.documents.SessionExercise;

public class SessionExerciseDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    private Integer order;

    private String repetitions;

    private Double weight;

    private Integer sets;

    private String exerciseId;

    public SessionExerciseDto() {
        // Empty for framework
    }

    public SessionExerciseDto(Integer order, String repetitions, Double weight, Integer sets, String exerciseId) {
        this.order = order;
        this.repetitions = repetitions;
        this.weight = weight;
        this.sets = sets;
        this.exerciseId = exerciseId;
    }

    public SessionExerciseDto(SessionExercise sessionExercise) {
        this(
                sessionExercise.getOrder(),
                sessionExercise.getRepetitions(),
                sessionExercise.getWeight(),
                sessionExercise.getSets(),
                sessionExercise.getExercise().getId()
        );
    }

    public Integer getOrder() {
        return this.order;
    }

    public String getRepetitions() {
        return this.repetitions;
    }

    public Double getWeight() {
        return this.weight;
    }

    public Integer getSets() {
        return this.sets;
    }

    public String getExerciseId() {
        return this.exerciseId;
    }

    @Override
    public String toString() {
        return "SessionExerciseDto { " +
                "id='" + this.id + '\'' +
                ", order='" + this.order + '\'' +
                ", repetitions='" + this.repetitions + '\'' +
                ", weight='" + this.weight + '\'' +
                ", sets='" + this.sets + '\'' +
                ", exerciseId='" + this.exerciseId + '\'' +
                " }";
    }
}
