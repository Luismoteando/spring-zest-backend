package es.upm.miw.spring_zest_backend.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MuscleGroupCreationDto {

    private String name;

    private List<String> muscleIds;

    public MuscleGroupCreationDto() {
        // Empty for framework
    }

    public MuscleGroupCreationDto(String name, List<String> muscleIds) {
        this.name = name;
        this.muscleIds = muscleIds;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMuscleIds() {
        return this.muscleIds;
    }

    public void setMuscleIds(List<String> muscleIds) {
        this.muscleIds = muscleIds;
    }
}
