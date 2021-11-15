package web.service;

import web.domain.Review;

import java.util.Map;
import java.util.UUID;

public interface ReviewService {
    Map<UUID, Review> findAll();

    Review getOneById(UUID id);

    Review save(Review person);

    void deleteById(UUID id);
}
