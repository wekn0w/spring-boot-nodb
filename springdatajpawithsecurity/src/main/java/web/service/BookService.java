package web.service;

import web.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> findAll();

    BookDto getOneById(Long id);

    BookDto save(BookDto person);

    void deleteById(Long id);
}
