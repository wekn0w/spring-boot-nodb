package web.service;

import web.domain.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll();

    Review getOneById(Long id);

    Review save(Review person);

    void deleteById(Long id);
}
