package web.dto;

import web.domain.Genre;

import java.util.List;
import java.util.Set;

public class BookDto {
    private Long id;
    private String name;
    Set<GenreDto> bookGenres;
    Set<AuthorDto> bookAuthors;

    public BookDto() {
    }

    public BookDto(String name) {
        this.name = name;
    }

    public BookDto(String name, Set<GenreDto> bookGenres, Set<AuthorDto> bookAuthors) {
        this.name = name;
        this.bookGenres = bookGenres;
        this.bookAuthors = bookAuthors;
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

    public Set<GenreDto> getBookGenres() {
        return bookGenres;
    }

    public void setBookGenres(Set<GenreDto> bookGenres) {
        this.bookGenres = bookGenres;
    }

    public Set<AuthorDto> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(Set<AuthorDto> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }
}
