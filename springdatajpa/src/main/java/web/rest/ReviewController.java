package web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import web.domain.Review;
import web.service.ReviewService;

import java.util.List;
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
    public List<Review> get() {
        return service.findAll();
    }

    @GetMapping("/review/{id}")
    public Review getById(@PathVariable("id") Long id) {
        Review review = service.getOneById(id);
        return review;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/review/")
    public Review create(@RequestBody Review dto) {
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
        Review review = service.getOneById(id);
        review.setComment(comment);
        service.save(review);
    }
}
