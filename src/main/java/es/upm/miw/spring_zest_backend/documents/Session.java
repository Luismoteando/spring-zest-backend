package es.upm.miw.spring_zest_backend.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class Session {

    @Id
    private String id;

    private String title;

    private Date start;

    private List<SessionExercise> sessionExercises;

    public Session() {
        // Empty for framework
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public List<SessionExercise> getSessionExercises() {
        return sessionExercises;
    }

    public void setSessionExercises(List<SessionExercise> sessionExercises) {
        this.sessionExercises = sessionExercises;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((Session) obj).id));
    }

    @Override
    public String toString() {
        return "Session { " +
                "id='" + this.id + '\'' +
                ", title='" + this.title + '\'' +
                ", start='" + this.start + '\'' +
                ", sessionExercises='" + this.sessionExercises + '\'' +
                " }";
    }

    public static class Builder {

        private Session session;

        private Builder() {
            this.session = new Session();
        }

        public Builder title(String title) {
            this.session.title = title;
            return this;
        }

        public Builder start(Date start) {
            this.session.start = start;
            return this;
        }

        public Builder sessionExercises(List<SessionExercise> sessionExercises) {
            this.session.sessionExercises = sessionExercises;
            return this;
        }

        public Session build() {
            return this.session;
        }
    }
}