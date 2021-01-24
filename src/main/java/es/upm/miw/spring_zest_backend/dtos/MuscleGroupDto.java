package es.upm.miw.spring_zest_backend.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.upm.miw.spring_zest_backend.documents.Exercise;
import es.upm.miw.spring_zest_backend.documents.Muscle;

import java.util.List;
import java.util.stream.Collectors;

public class MuscleGroupDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    private String name;

    private List<String> muscleIds;

    public MuscleGroupDto() {
        // Empty for framework
    }

    public MuscleGroupDto(String name, List<String> muscleIds) {
        this.name = name;
        this.muscleIds = muscleIds;
    }

    public MuscleGroupDto(Exercise exercise) {
        this(
                exercise.getName(),
                exercise.getMuscles().stream()
                        .map(Muscle::getId)
                        .collect(Collectors.toList())
        );
    }

    public String getName() {
        return this.name;
    }

    public List<String> getMuscleIds() {
        return this.muscleIds;
    }

    @Override
    public String toString() {
        return "MuscleGroupDto { " +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", muscleIds='" + this.muscleIds + '\'' +
                " }";
    }
}
