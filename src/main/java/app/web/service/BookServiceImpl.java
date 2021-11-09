package app.web.service;

import app.web.domain.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final List<Book> collection = new ArrayList<>();

    @Override
    public List<Book> findAll() {
        return collection;
    }

    @Override
    public Book getOneById(long id) {
        Book finded = null;
        for (Book item : collection) {
            if (id == (item.getId())) {
                finded = item;
                break;
            }
        }
        return finded;
    }

    @Override
    public Book save(Book book) {
        Book updated = getOneById(book.getId());
        return updated != null ? updateItem(updated, book) : insertItem(book);
    }

    private Book insertItem(Book newBook) {
        collection.add(newBook);
        return newBook;
    }

    private Book updateItem(Book oldBook, Book newBook) {
        oldBook.setBookGenre(newBook.getBookGenre());
        oldBook.setName(newBook.getName());
        return oldBook;
    }

    @Override
    public void deleteById(long id) {
        Book finded = getOneById(id);
        if (finded != null)
            collection.remove(finded);
    }
}
