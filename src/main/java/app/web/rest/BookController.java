package app.web.rest;

import app.web.domain.Book;
import app.web.service.BookService;
import app.web.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
public class BookController {

    private final BookService service;

    private final GenreService genreService;

    @Autowired
    public BookController(BookService service, GenreService genreService) {
        this.service = service;
        this.genreService = genreService;
    }

    @GetMapping(value = "/book")
    public Map<UUID, Book> get() {
        return service.findAll();
    }

    @GetMapping("/book/{id}")
    public Book getById(@PathVariable("id") UUID id) {
        Book book = service.getOneById(id);
        return book;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/book/")
    public Book create(@RequestBody Book dto) {
        //todo подумать над эксепшеном
        if (dto.getBookGenre() == null || dto.getBookGenre().getId() == null || genreService.getOneById(dto.getBookGenre().getId()) == null)
            throw new NoSuchElementException();
        return service.save(dto);
    }

    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable("id") UUID id) {
        service.deleteById(id);
    }

    @PutMapping("/book/{id}/name")
    public void changeName(
            @PathVariable("id") UUID id,
            @RequestParam("name") String name
    ) {
        Book book = service.getOneById(id);
        book.setName(name);
        service.save(book);
    }
}
