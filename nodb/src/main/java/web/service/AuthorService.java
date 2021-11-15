package web.service;

import web.domain.Author;

import java.util.Map;
import java.util.UUID;

public interface AuthorService {
    Map<UUID, Author> findAll();

    Author getOneById(UUID id);

    Author save(Author person);

    void deleteById(UUID id);
}