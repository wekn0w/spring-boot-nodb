package app.web.domain;

import java.util.UUID;

public class Review {
    private Long id;
    private String comment;

    public Review() {
    }

    public Review(String comment) {
        this.id = UUID.randomUUID().getMostSignificantBits();
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
