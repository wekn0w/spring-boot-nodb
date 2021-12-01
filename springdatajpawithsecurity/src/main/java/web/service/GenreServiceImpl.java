package web.service;

import org.springframework.stereotype.Service;
import web.domain.Genre;
import web.dto.GenreDto;
import web.repo.GenreRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepo genreRepository;

    public GenreServiceImpl(GenreRepo genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreDto> findAll() {
        List<Genre> genres = genreRepository.findAll();
        List<GenreDto> resultList = new ArrayList<>();
        genres.forEach(i -> resultList.add(new GenreDto(i.getId(), i.getName())));
        return resultList;
    }

    @Override
    public GenreDto getOneById(Long id) {
        Genre stored = genreRepository.findById(id).orElse(new Genre());
        return new GenreDto(stored.getId(), stored.getName());
    }

    @Transactional
    @Override
    public GenreDto save(GenreDto genre) {
        Genre newGenre = genreRepository.findById(genre.getId()).orElse(new Genre());
        if (genre.getName() != null && !genre.getName().isEmpty()) {
            newGenre.setName(genre.getName());
        }
        Genre saved = genreRepository.save(newGenre);
        return new GenreDto(saved.getId(), saved.getName());
    }

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }
}
