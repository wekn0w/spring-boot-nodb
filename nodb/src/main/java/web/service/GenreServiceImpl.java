package web.service;

import web.domain.Genre;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class GenreServiceImpl implements GenreService {

    private Map<UUID, Genre> collection = new HashMap<>();

    @Override
    public Map<UUID, Genre> findAll() {
        return collection;
    }

    @Override
    public Genre getOneById(UUID id) {
        Map.Entry<UUID, Genre> entry = collection.entrySet().stream().filter(item -> id.equals(item.getKey())).findFirst().orElse(null);
        return entry != null ? entry.getValue() : null;
    }

    @Override
    public Genre save(Genre genre) {
        Genre updated = getOneById(genre.getId());
        return updated != null ? updateItem(updated, genre) : insertItem(genre);
    }

    @Override
    public void deleteById(UUID id) {
        Genre finded = getOneById(id);
        if (finded != null)
            collection.remove(finded);
    }

    private Genre insertItem(Genre newGenre) {
        UUID id = UUID.randomUUID();
        Genre genre = new Genre(id, newGenre.getName());
        collection.put(id, genre);
        return genre;
    }

    private Genre updateItem(Genre oldGenre, Genre newGenre) {
        collection.put(oldGenre.getId(), newGenre);
        return newGenre;
    }
}
