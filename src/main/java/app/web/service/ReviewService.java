package app.web.service;

import app.web.domain.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll();

    Review getOneById(long id);

    Review save(Review person);

    void deleteById(long id);
}
