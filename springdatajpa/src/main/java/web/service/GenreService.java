package web.service;

import web.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();

    Genre getOneById(Long id);

    Genre save(Genre person);

    void deleteById(Long id);
}
