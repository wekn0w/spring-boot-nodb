package app.web.service;

import app.web.domain.Genre;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{

    private final List<Genre> collection = new ArrayList<>();

    @Override
    public List<Genre> findAll() {
        return collection;
    }

    @Override
    public Genre getOneById(long id) {
        Genre finded = null;
        for (Genre item : collection) {
            if (id == (item.getId())) {
                finded = item;
                break;
            }
        }
        return finded;
    }

    @Override
    public Genre save(Genre genre) {
        Genre updated = getOneById(genre.getId());
        return updated != null ? updateItem(updated, genre) : insertItem(genre);
    }

    private Genre insertItem(Genre newGenre) {
        collection.add(newGenre);
        return newGenre;
    }

    private Genre updateItem(Genre oldGenre, Genre newGenre) {
        oldGenre.setName(newGenre.getName());
        return oldGenre;
    }

    @Override
    public void deleteById(long id) {
        Genre finded = getOneById(id);
        if (finded != null)
            collection.remove(finded);
    }
}
