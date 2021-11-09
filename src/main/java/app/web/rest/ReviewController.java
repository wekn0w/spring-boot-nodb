package app.web.rest;

import app.web.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import app.web.service.ReviewService;

import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @RequestMapping(
            value = "/review",
            method = RequestMethod.GET
    )
    //todo слой dto
    public List<Review> get() {
        return service.findAll();
    }

    @GetMapping("/review/{id}")
    //todo слой dto
    public Review getById(@PathVariable("id") int id) {
        Review review = service.getOneById(id);
        return review;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/review/")
    //todo слой dto
    public Review create(@RequestBody Review dto) {
        return service.save(dto);
    }

    @DeleteMapping("/review/{id}")
    public void delete(@PathVariable("id") int id) {
        service.deleteById(id);
    }

    @PutMapping("/review/{id}/comment")
    public void changeName(
            @PathVariable("id") int id,
            @RequestParam("comment") String comment
    ) {
        Review review = service.getOneById(id);
        review.setComment(comment);
        service.save(review);
    }
}
