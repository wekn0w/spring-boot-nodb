package web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.domain.Book;
import web.service.BookService;
import web.service.GenreService;

import java.util.List;

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
    public List<Book> get() {
        return service.findAll();
    }

    @GetMapping("/book/{id}")
    public Book getById(@PathVariable("id") Long id) {
        return service.getOneById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/book/")
    public Book create(@RequestBody Book dto) {
        return service.save(dto);
    }

    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PutMapping("/book/{id}/name")
    public void changeName(
            @PathVariable("id") Long id,
            @RequestParam("name") String name
    ) {
        Book book = service.getOneById(id);
        book.setName(name);
        service.save(book);
    }
}
