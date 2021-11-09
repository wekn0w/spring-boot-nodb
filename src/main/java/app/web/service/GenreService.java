package app.web.service;

import app.web.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();

    Genre getOneById(long id);

    Genre save(Genre person);

    void deleteById(long id);
}
