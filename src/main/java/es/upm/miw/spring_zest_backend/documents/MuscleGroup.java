package es.upm.miw.spring_zest_backend.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class MuscleGroup {

    @Id
    private String id;

    private String name;

    @DBRef(lazy = true)
    private List<Muscle> muscles;

    public MuscleGroup() {
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

    public List<Muscle> getMuscles() {
        return muscles;
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
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((MuscleGroup) obj).id));
    }

    @Override
    public String toString() {
        return "MuscleGroup { " +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", muscles='" + this.muscles + '\'' +
                " }";
    }

    public static class Builder {

        private MuscleGroup exercise;

        private Builder() {
            this.exercise = new MuscleGroup();
        }

        public Builder name(String name) {
            this.exercise.name = name;
            return this;
        }

        public Builder muscles(List<Muscle> muscles) {
            this.exercise.muscles = muscles;
            return this;
        }

        public MuscleGroup build() {
            return this.exercise;
        }
    }
}