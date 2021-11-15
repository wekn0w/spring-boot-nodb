package web.service;

import web.domain.Genre;

import java.util.Map;
import java.util.UUID;

public interface GenreService {
    Map<UUID, Genre> findAll();

    Genre getOneById(UUID id);

    Genre save(Genre person);

    void deleteById(UUID id);
}
