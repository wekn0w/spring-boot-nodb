package web.service;

import javassist.NotFoundException;
import web.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    Author getOneById(Long id) throws NotFoundException;

    Author save(Author person);

    void deleteById(Long id);
}