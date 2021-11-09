package app.web.domain;

import java.util.UUID;

public class Book {

    private Long id;
    private String name;
    private Genre bookGenre;

    public Book() {
        this.id = UUID.randomUUID().getMostSignificantBits();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(Genre bookGenre) {
        this.bookGenre = bookGenre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
