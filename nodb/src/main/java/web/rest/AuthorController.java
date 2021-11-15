package web.rest;

import web.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.service.AuthorService;

import java.util.Map;
import java.util.UUID;

@RestController
public class AuthorController {

    private final AuthorService service;

    @Autowired
    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping(value = "/author")
    public Map<UUID, Author> get() {
        return service.findAll();
    }

    @GetMapping("/author/{id}")
    public Author getById(@PathVariable("id") UUID id) {
        Author person = service.getOneById(id);
        return person;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/author/")
    public Author create(@RequestBody Author dto) {
        return service.save(dto);
    }

    @DeleteMapping("/author/{id}")
    public void delete(@PathVariable("id") UUID id) {
        service.deleteById(id);
    }

    @PutMapping("/author/{id}/name")
    public void changeName(
            @PathVariable("id") UUID id,
            @RequestParam("name") String name
    ) {
        Author person = service.getOneById(id);
        person.setFullname(name);
        service.save(person);
    }
}
