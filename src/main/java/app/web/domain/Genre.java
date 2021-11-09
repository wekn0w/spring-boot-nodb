package app.web.domain;

import java.util.UUID;

public class Genre {
    private Long id;
    private String name;

    public Genre() {
        this.id = UUID.randomUUID().getMostSignificantBits();
    }

    public Genre(BookGenres name) {
        this.id = UUID.randomUUID().getMostSignificantBits();
        this.name = name.getDisplayValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
