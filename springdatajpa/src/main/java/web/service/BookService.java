package web.service;

import web.dto.BookDto;

import javax.transaction.Transactional;
import java.util.List;

public interface BookService {
    List<BookDto> findAll();

    BookDto getOneById(Long id);

    @Transactional
    BookDto save(BookDto person);

    @Transactional
    void deleteById(Long id);
}
