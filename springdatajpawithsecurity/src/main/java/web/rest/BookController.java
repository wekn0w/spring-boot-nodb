package web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import web.dto.BookDto;
import web.service.BookService;
import web.service.GenreService;

import java.util.List;

@RestController
public class BookController {

    private final BookService service;

    private final GenreService genreService;

    public BookController(BookService service, GenreService genreService) {
        this.service = service;
        this.genreService = genreService;
    }

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @GetMapping(value = "/book")
    public List<BookDto> get() {
        return service.findAll();
    }

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @GetMapping("/book/{id}")
    public BookDto getById(@PathVariable("id") Long id) {
        return service.getOneById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/book/")
    public BookDto create(@RequestBody BookDto dto) {
        return service.save(dto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/book/{id}/name")
    public void changeName(
            @PathVariable("id") Long id,
            @RequestParam("name") String name
    ) {
        BookDto book = service.getOneById(id);
        book.setName(name);
        service.save(book);
    }
}
