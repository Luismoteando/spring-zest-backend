package es.upm.miw.spring_zest_backend.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Exercise {

    @Id
    private String id;

    private String name;

    @DBRef(lazy = true)
    private Workout workout;

    @DBRef(lazy = true)
    private List<Muscle> muscles;

    public Exercise() {
        // Empty for framework
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Workout getWorkout() {
        return this.workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public List<Muscle> getMuscles() {
        return this.muscles;
    }

    public void setMuscles(List<Muscle> muscles) {
        this.muscles = muscles;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((Exercise) obj).id));
    }

    @Override
    public String toString() {
        return "Exercise { " +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", workout='" + this.workout + '\'' +
                ", muscles='" + this.muscles + '\'' +
                " }";
    }

    public static class Builder {

        private Exercise exercise;

        private Builder() {
            this.exercise = new Exercise();
        }

        public Builder name(String name) {
            this.exercise.name = name;
            return this;
        }

        public Builder workout(Workout workout) {
            this.exercise.workout = workout;
            return this;
        }

        public Builder muscles(List<Muscle> muscles) {
            this.exercise.muscles = muscles;
            return this;
        }

        public Exercise build() {
            return this.exercise;
        }
    }
}