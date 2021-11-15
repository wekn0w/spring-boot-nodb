package web.service;

import web.domain.Book;

import java.util.Map;
import java.util.UUID;

public interface BookService {
    Map<UUID, Book> findAll();

    Book getOneById(UUID id);

    Book save(Book person);

    void deleteById(UUID id);
}
