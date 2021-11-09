package app.web.rest;

import app.web.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import app.web.service.GenreService;

import java.util.List;

@RestController
public class GenreController {

    private final GenreService service;

    @Autowired
    public GenreController(GenreService service) {
        this.service = service;
    }

    @RequestMapping(
            value = "/genre",
            method = RequestMethod.GET
    )
    //todo слой dto
    public List<Genre> get() {
        return service.findAll();
    }

    @GetMapping("/genre/{id}")
    //todo слой dto
    public Genre getById(@PathVariable("id") int id) {
        Genre genre = service.getOneById(id);
        return genre;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/genre/")
    //todo слой dto
    public Genre create(@RequestBody Genre dto) {
        return service.save(dto);
    }

    @DeleteMapping("/genre/{id}")
    public void delete(@PathVariable("id") int id) {
        service.deleteById(id);
    }

    @PutMapping("/genre/{id}/name")
    public void changeName(
            @PathVariable("id") int id,
            @RequestParam("name") String name
    ) {
        Genre genre = service.getOneById(id);
        genre.setName(name);
        service.save(genre);
    }
}
