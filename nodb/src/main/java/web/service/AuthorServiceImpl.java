package web.service;

import web.domain.Author;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorService {

    private Map<UUID, Author> collection = new HashMap<>();

    @Override
    public Map<UUID, Author> findAll() {
        return collection;
    }

    @Override
    public Author getOneById(UUID id) {
        Map.Entry<UUID, Author> entry = collection.entrySet().stream().filter(item -> id.equals(item.getKey())).findFirst().orElse(null);
        return entry != null ? entry.getValue() : null;
    }

    @Override
    public Author save(Author person) {
        Author updated = getOneById(person.getId());
        return updated != null ? updateItem(updated, person) : insertItem(person);
    }

    @Override
    public void deleteById(UUID id) {
        Author finded = getOneById(id);
        if (finded != null)
            collection.remove(finded);
    }


    private Author insertItem(Author newAuthor) {
        UUID id = UUID.randomUUID();
        Author person = new Author(id, newAuthor.getFullname(), newAuthor.getAge());
        collection.put(id, person);
        return person;
    }

    private Author updateItem(Author oldAuthor, Author newAuthor) {
        collection.put(oldAuthor.getId(), newAuthor);
        return newAuthor;
    }

}
