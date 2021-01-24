package es.upm.miw.spring_zest_backend.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.upm.miw.spring_zest_backend.documents.Muscle;

public class MuscleDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    private String name;

    public MuscleDto() {
        // Empty for framework
    }

    public MuscleDto(String name) {
        this.name = name;
    }

    public MuscleDto(Muscle muscle) {
        this(muscle.getName());
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "MuscleGroupDto { " +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                " }";
    }
}
