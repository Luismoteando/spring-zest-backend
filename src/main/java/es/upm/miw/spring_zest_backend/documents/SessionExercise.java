package es.upm.miw.spring_zest_backend.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SessionExercise {

    @Id
    private String id;

    private Integer order;

    private String repetitions;

    private Double weight;

    private Integer sets;

    @DBRef(lazy = true)
    private Exercise exercise;

    public SessionExercise() {
        // Empty for framework
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
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

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((SessionExercise) obj).id));
    }

    @Override
    public String toString() {
        return "SessionExercise { " +
                "id='" + this.id + '\'' +
                ", order='" + this.order + '\'' +
                ", repetitions='" + this.repetitions + '\'' +
                ", weight='" + this.weight + '\'' +
                ", sets='" + this.sets + '\'' +
                ", exercise='" + this.exercise + '\'' +
                " }";
    }

    public static class Builder {

        private SessionExercise workoutSession;

        private Builder() {
            this.workoutSession = new SessionExercise();
        }

        public Builder order(Integer order) {
            this.workoutSession.order = order;
            return this;
        }

        public Builder repetitions(String repetitions) {
            this.workoutSession.repetitions = repetitions;
            return this;
        }

        public Builder weight(Double weight) {
            this.workoutSession.weight = weight;
            return this;
        }

        public Builder sets(Integer sets) {
            this.workoutSession.sets = sets;
            return this;
        }

        public Builder exercise(Exercise exercise) {
            this.workoutSession.exercise = exercise;
            return this;
        }

        public SessionExercise build() {
            return this.workoutSession;
        }
    }
}