package web.rest;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.domain.Author;
import web.service.AuthorService;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService service;

    @Autowired
    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping(value = "/author")
    public List<Author> get() {
        return service.findAll();
    }

    @GetMapping("/author/{id}")
    public Author getById(@PathVariable("id") Long id) throws NotFoundException {
        return service.getOneById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/author/")
    public Author create(@RequestBody Author dto) {
        return service.save(dto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/author/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PutMapping("/author/{id}/name")
    public void changeName(
            @PathVariable("id") Long id,
            @RequestParam("name") String name
    ) throws NotFoundException {
        Author person = service.getOneById(id);
        person.setFullname(name);
        service.save(person);
    }
}