package es.upm.miw.spring_zest_backend.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Muscle {

    @Id
    private String id;

    private String name;

    public Muscle() {
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

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((Muscle) obj).id));
    }

    @Override
    public String toString() {
        return "Muscle { " +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                " }";
    }

    public static class Builder {

        private Muscle muscle;

        private Builder() {
            this.muscle = new Muscle();
        }

        public Builder name(String name) {
            this.muscle.name = name;
            return this;
        }

        public Muscle build() {
            return this.muscle;
        }
    }
}