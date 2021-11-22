package web.rest;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.dto.AuthorDto;
import web.service.AuthorService;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping(value = "/author")
    public List<AuthorDto> get() {
        return service.findAll();
    }

    @GetMapping("/author/{id}")
    public AuthorDto getById(@PathVariable("id") Long id) throws NotFoundException {
        return service.getOneById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/author/")
    public AuthorDto create(@RequestBody AuthorDto dto) {
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
        AuthorDto person = service.getOneById(id);
        person.setFullname(name);
        service.save(person);
    }
}
