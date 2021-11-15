package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.domain.Book;
import web.domain.Genre;
import web.repo.BookRepo;
import web.repo.GenreRepo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepository;
    @Autowired
    private GenreRepo genreRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getOneById(Long id) {
        return bookRepository.findById(id).orElse(new Book());
    }

    @Override
    public Book save(Book book) {
        Book newBook = bookRepository.findById(book.getId()).orElse(new Book());
        if (book.getName() != null && !book.getName().isEmpty()) {
            newBook.setName(book.getName());
        }
        if (book.getBookGenre() != null) {
            Optional<Genre> genreById = genreRepository.findById(book.getId());
            genreById.ifPresent(newBook::setBookGenre);
        }
        return bookRepository.save(newBook);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
