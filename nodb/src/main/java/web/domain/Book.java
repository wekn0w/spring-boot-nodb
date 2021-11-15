package web.domain;

import java.util.UUID;

public class Book {

    private UUID id;
    private String name;
    private Genre bookGenre;

    public Book(UUID id, String name, Genre bookGenre) {
        this.id = id;
        this.name = name;
        this.bookGenre = bookGenre;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
