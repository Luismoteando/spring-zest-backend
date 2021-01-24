package es.upm.miw.spring_zest_backend.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.upm.miw.spring_zest_backend.documents.Session;
import es.upm.miw.spring_zest_backend.documents.SessionExercise;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SessionDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    private String title;

    private Date start;

    private List<String> sessionExerciseIds;

    public SessionDto() {
        // Empty for framework
    }

    public SessionDto(String id, String title, Date start, List<String> sessionExerciseIds) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.sessionExerciseIds = sessionExerciseIds;
    }

    public SessionDto(Session session) {
        this(
                session.getId(),
                session.getTitle(),
                session.getStart(),
                session.getSessionExercises().stream()
                        .map(SessionExercise::getId)
                        .collect(Collectors.toList())
        );
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public Date getStart() {
        return this.start;
    }

    public List<String> getSessionExerciseIds() {
        return this.sessionExerciseIds;
    }

    @Override
    public String toString() {
        return "SessionDto { " +
                "id='" + this.id + '\'' +
                ", title='" + this.title + '\'' +
                ", start='" + this.start + '\'' +
                ", sessionExerciseIds='" + this.sessionExerciseIds + '\'' +
                " }";
    }
}
