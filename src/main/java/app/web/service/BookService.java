package app.web.service;

import app.web.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book getOneById(long id);

    Book save(Book person);

    void deleteById(long id);
}
