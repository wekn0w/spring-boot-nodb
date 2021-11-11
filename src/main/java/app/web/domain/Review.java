package app.web.domain;

import java.util.UUID;

public class Review {
    private UUID id;
    private String comment;

    public Review() {
    }

    public Review(UUID id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
