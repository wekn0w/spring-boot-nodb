package web.service;

import web.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book getOneById(Long id);

    Book save(Book person);

    void deleteById(Long id);
}
