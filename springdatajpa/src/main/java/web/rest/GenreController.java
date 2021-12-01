package web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.dto.GenreDto;
import web.service.GenreService;

import java.util.List;

@RestController
public class GenreController {

    private final GenreService service;

    public GenreController(GenreService service) {
        this.service = service;
    }

    @GetMapping(value = "/genre")
    public List<GenreDto> get() {
        return service.findAll();
    }

    @GetMapping("/genre/{id}")
    public GenreDto getById(@PathVariable("id") Long id) {
        return service.getOneById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/genre/")
    public GenreDto create(@RequestBody GenreDto dto) {
        return service.save(dto);
    }

    @DeleteMapping("/genre/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PutMapping("/genre/{id}/name")
    public void changeName(
            @PathVariable("id") Long id,
            @RequestParam("name") String name
    ) {
        GenreDto genre = service.getOneById(id);
        genre.setName(name);
        service.save(genre);
    }
}
