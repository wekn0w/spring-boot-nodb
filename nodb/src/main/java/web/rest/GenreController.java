package web.rest;

import web.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.service.GenreService;

import java.util.Map;
import java.util.UUID;

@RestController
public class GenreController {

    private final GenreService service;

    @Autowired
    public GenreController(GenreService service) {
        this.service = service;
    }

    @GetMapping(value = "/genre")
    public Map<UUID, Genre> get() {
        return service.findAll();
    }

    @GetMapping("/genre/{id}")
    public Genre getById(@PathVariable("id") UUID id) {
        Genre genre = service.getOneById(id);
        return genre;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/genre/")
    public Genre create(@RequestBody Genre dto) {
        return service.save(dto);
    }

    @DeleteMapping("/genre/{id}")
    public void delete(@PathVariable("id") UUID id) {
        service.deleteById(id);
    }

    @PutMapping("/genre/{id}/name")
    public void changeName(
            @PathVariable("id") UUID id,
            @RequestParam("name") String name
    ) {
        Genre genre = service.getOneById(id);
        genre.setName(name);
        service.save(genre);
    }
}
