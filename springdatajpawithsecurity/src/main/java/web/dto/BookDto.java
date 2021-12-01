package web.dto;

import java.util.Set;

public class BookDto {
    private Long id;
    private String name;
    GenreDto bookGenre;
    Set<AuthorDto> bookAuthors;

    public BookDto() {
    }

    public BookDto(String name) {
        this.name = name;
    }

    public BookDto(String name, GenreDto bookGenre, Set<AuthorDto> bookAuthors) {
        this.name = name;
        this.bookGenre = bookGenre;
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

    public GenreDto getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(GenreDto bookGenre) {
        this.bookGenre = bookGenre;
    }

    public Set<AuthorDto> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(Set<AuthorDto> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }
}
