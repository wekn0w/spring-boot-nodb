package web.rest;

import web.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.service.ReviewService;

import java.util.Map;
import java.util.UUID;

@RestController
public class ReviewController {

    private final ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping(value = "/review")
    public Map<UUID, Review> get() {
        return service.findAll();
    }

    @GetMapping("/review/{id}")
    public Review getById(@PathVariable("id") UUID id) {
        Review review = service.getOneById(id);
        return review;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/review/")
    public Review create(@RequestBody Review dto) {
        return service.save(dto);
    }

    @DeleteMapping("/review/{id}")
    public void delete(@PathVariable("id") UUID id) {
        service.deleteById(id);
    }

    @PutMapping("/review/{id}/comment")
    public void changeName(
            @PathVariable("id") UUID id,
            @RequestParam("comment") String comment
    ) {
        Review review = service.getOneById(id);
        review.setComment(comment);
        service.save(review);
    }
}
