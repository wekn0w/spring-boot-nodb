package app.web.service;

import app.web.domain.Author;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AuthorServiceImpl implements AuthorService {

    private final List<Author> collection = new ArrayList<>();//сет не лучше?

    @Override
    public List<Author> findAll() {
        return collection;
    }

    @Override
    public Author getOneById(long id) {
        Author finded = null;
        for (Author item : collection) {//можно упростить запись?
            if (id == (item.getId())) {
                finded = item;
                break;
            }
        }
        return finded;
    }

    @Override
    public Author save(Author person) {
        Author updated = getOneById(person.getId());
        return updated != null ? updateItem(updated, person) : insertItem(person);
    }

    private Author insertItem(Author newAuthor) {
        collection.add(newAuthor);
        return newAuthor;
    }

    private Author updateItem(Author oldAuthor, Author newAuthor) {
        oldAuthor.setFullname(newAuthor.getFullname());
        oldAuthor.setAge(newAuthor.getAge());
        return oldAuthor;
    }

    @Override
    public void deleteById(long id) {
        Author finded = getOneById(id);
        if (finded != null)
            collection.remove(finded);
    }
}
