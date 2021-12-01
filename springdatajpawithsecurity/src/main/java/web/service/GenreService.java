package web.service;

import web.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> findAll();

    GenreDto getOneById(Long id);

    GenreDto save(GenreDto genre);

    void deleteById(Long id);
}
