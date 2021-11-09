package app.web.rest;

import app.web.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import app.web.service.BookService;

import java.util.List;

@RestController
public class BookController {

    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @RequestMapping(
            value = "/book",
            method = RequestMethod.GET
    )
    //todo слой dto
    public List<Book> get() {
        return service.findAll();
    }

    @GetMapping("/book/{id}")
    //todo слой dto
    public Book getById(@PathVariable("id") int id) {
        Book book = service.getOneById(id);
        return book;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/book/")
    //todo слой dto
    public Book create(@RequestBody Book dto) {
        return service.save(dto);
    }

    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable("id") int id) {
        service.deleteById(id);
    }

    @PutMapping("/book/{id}/name")
    public void changeName(
            @PathVariable("id") int id,
            @RequestParam("name") String name
    ) {
        Book book = service.getOneById(id);
        book.setName(name);
        service.save(book);
    }
}
