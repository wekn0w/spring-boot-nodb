package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.domain.Genre;
import web.repo.GenreRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepo genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getOneById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Override
    public Genre save(Genre genre) {
        Genre newGenre = genreRepository.findById(genre.getId()).orElse(new Genre());
        if (genre.getName() != null && !genre.getName().isEmpty()) {
            newGenre.setName(genre.getName());
        }
        return genreRepository.save(newGenre);
    }

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }
}
