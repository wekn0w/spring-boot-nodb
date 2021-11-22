package web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.dto.ReviewDto;
import web.service.ReviewService;

import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping(value = "/review")
    public List<ReviewDto> get() {
        return service.findAll();
    }

    @GetMapping("/review/{id}")
    public ReviewDto getById(@PathVariable("id") Long id) {
        return service.getOneById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/review/")
    public ReviewDto create(@RequestBody ReviewDto dto) {
        return service.save(dto);
    }

    @DeleteMapping("/review/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PutMapping("/review/{id}/comment")
    public void changeName(
            @PathVariable("id") Long id,
            @RequestParam("comment") String comment
    ) {
        ReviewDto review = service.getOneById(id);
        review.setComment(comment);
        service.save(review);
    }
}
