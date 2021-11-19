package web.utils;

import web.domain.Book;
import web.dto.AuthorDto;
import web.dto.BookDto;
import web.dto.GenreDto;

import java.util.HashSet;
import java.util.Set;

public class BookAdapter {

    public BookDto convertToDto(Book saved) {
        if (saved == null)
            return null;
        BookDto dto = new BookDto(saved.getName());
        dto.setId(saved.getId());
        Set<GenreDto> genreDtoSet = new HashSet<>();
        if (saved.getBookGenres() != null)
            saved.getBookGenres().forEach(i -> genreDtoSet.add(new GenreDto(i.getId(), i.getName())));
        dto.setBookGenres(genreDtoSet);
        Set<AuthorDto> authorDtoSet = new HashSet<>();
        if (saved.getAuthors() != null) {
            saved.getAuthors().forEach(i -> authorDtoSet.add(new AuthorDto(i.getId(), i.getFullname(), i.getAge())));
        }
        dto.setBookAuthors(authorDtoSet);
        return dto;
    }
}
