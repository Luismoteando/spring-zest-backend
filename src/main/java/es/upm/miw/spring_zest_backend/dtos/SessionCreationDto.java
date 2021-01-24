package es.upm.miw.spring_zest_backend.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionCreationDto {

    private String title;

    private Date start;

    private List<String> sessionExerciseIds;

    public SessionCreationDto() {
        // Empty for framework
    }

    public SessionCreationDto(String title, Date start, List<String> sessionExerciseIds) {
        this.title = title;
        this.start = start;
        this.sessionExerciseIds = sessionExerciseIds;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return this.start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public List<String> getSessionExerciseIds() {
        return this.sessionExerciseIds;
    }

    public void setSessionExerciseIds(List<String> sessionExerciseIds) {
        this.sessionExerciseIds = sessionExerciseIds;
    }
}
