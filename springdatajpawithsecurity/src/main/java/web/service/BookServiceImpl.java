package web.service;

import org.springframework.stereotype.Service;
import web.domain.Author;
import web.domain.Book;
import web.dto.AuthorDto;
import web.dto.BookDto;
import web.repo.AuthorRepo;
import web.repo.BookRepo;
import web.repo.GenreRepo;
import web.utils.BookAdapter;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class BookServiceImpl implements BookService {

    private BookRepo bookRepository;
    private GenreRepo genreRepository;
    private AuthorRepo authorRepository;
    private BookAdapter bookAdapter;

    public BookServiceImpl(BookRepo bookRepository, GenreRepo genreRepository, AuthorRepo authorRepository, BookAdapter bookAdapter) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.bookAdapter = bookAdapter;
    }

    @Override
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> resultList = new ArrayList<>();
        for (Book record : books) {
            BookDto dto = bookAdapter.convertToDto(record);
            resultList.add(dto);
        }
        return resultList;
    }

    @Override
    public BookDto getOneById(Long id) {
        Book stored = bookRepository.findById(id).orElse(new Book());
        return new BookDto(stored.getName());
    }

    @Transactional
    @Override
    public BookDto save(BookDto book) {
        Book newBook = bookRepository.findById(book.getId()).orElse(new Book());
        if (book.getName() != null && !book.getName().isEmpty()) {
            newBook.setName(book.getName());
        }
        if (book.getBookGenre() != null) {
            newBook.setBookGenre(genreRepository.findById(book.getBookGenre().getId()).orElse(null));
        }
        if (book.getBookAuthors() != null) {
            List<Author> authorById = authorRepository.findAllById(book.getBookAuthors().stream().map(AuthorDto::getId).collect(toSet()));
            Set<Author> authors = Set.copyOf(authorById);
            newBook.setAuthors(authors);
        }
        Book saved = bookRepository.save(newBook);
        return bookAdapter.convertToDto(saved);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
