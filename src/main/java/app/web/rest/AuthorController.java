package app.web.rest;

import app.web.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import app.web.service.AuthorService;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService service;

    @Autowired
    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @RequestMapping(
            value = "/author",
            method = RequestMethod.GET
    )
    //todo слой dto
    public List<Author> get() {
        return service.findAll();
    }

    @GetMapping("/author/{id}")
    //todo слой dto
    public Author getById(@PathVariable("id") int id) {
        Author person = service.getOneById(id);
        return person;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/author/")
    //todo слой dto
    public Author create(@RequestBody Author dto) {
        return service.save(dto);
    }

    @DeleteMapping("/author/{id}")
    public void delete(@PathVariable("id") int id) {
        service.deleteById(id);
    }

    @PutMapping("/author/{id}/name")
    public void changeName(
            @PathVariable("id") int id,
            @RequestParam("name") String name
    ) {
        Author person = service.getOneById(id);
        person.setFullname(name);
        service.save(person);
    }
}
