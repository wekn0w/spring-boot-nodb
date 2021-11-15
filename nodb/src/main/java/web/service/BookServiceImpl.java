package web.service;

import web.domain.Book;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    private Map<UUID, Book> collection = new HashMap<>();

    @Override
    public Map<UUID, Book> findAll() {
        return collection;
    }

    @Override
    public Book getOneById(UUID id) {
        Map.Entry<UUID, Book> entry = collection.entrySet().stream().filter(item -> id.equals(item.getKey())).findFirst().orElse(null);
        return entry != null ? entry.getValue() : null;
    }

    @Override
    public Book save(Book book) {
        Book updated = getOneById(book.getId());
        return updated != null ? updateItem(updated, book) : insertItem(book);
    }

    @Override
    public void deleteById(UUID id) {
        Book finded = getOneById(id);
        if (finded != null)
            collection.remove(finded);
    }

    private Book insertItem(Book newBook) {
        UUID id = UUID.randomUUID();
        Book book = new Book(id, newBook.getName(), newBook.getBookGenre());
        collection.put(id, book);
        return book;
    }

    private Book updateItem(Book oldBook, Book newBook) {
        collection.put(oldBook.getId(), newBook);
        return newBook;
    }
}
