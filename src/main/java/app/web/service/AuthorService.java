package app.web.service;

import app.web.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    Author getOneById(long id);

    Author save(Author person);

    void deleteById(long id);
}