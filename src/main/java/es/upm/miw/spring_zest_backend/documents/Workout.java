package es.upm.miw.spring_zest_backend.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Workout {

    @Id
    private String id;

    private String name;

    private String goal;

    private Long duration;

    public Workout() {
        // Empty for framework
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return this.id;
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

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((Workout) obj).id));
    }

    @Override
    public String toString() {
        return "Workout { " +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", goal='" + this.goal + '\'' +
                ", duration='" + this.duration + '\'' +
                " }";
    }

    public static class Builder {

        private Workout workout;

        private Builder() {
            this.workout = new Workout();
            this.workout.duration = 900000L;
        }

        public Builder name(String name) {
            this.workout.name = name;
            return this;
        }

        public Builder goal(String goal) {
            this.workout.goal = goal;
            return this;
        }

        public Builder duration(Long description) {
            this.workout.duration = description;
            return this;
        }

        public Workout build() {
            return this.workout;
        }
    }
}