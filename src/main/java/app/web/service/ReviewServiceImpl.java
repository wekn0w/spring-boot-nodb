package app.web.service;

import app.web.domain.Review;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {

    private Map<UUID, Review> collection = new HashMap<>();

    @Override
    public Map<UUID, Review> findAll() {
        return collection;
    }

    @Override
    public Review getOneById(UUID id) {
        Map.Entry<UUID, Review> entry = collection.entrySet().stream().filter(item -> id.equals(item.getKey())).findFirst().orElse(null);
        return entry != null ? entry.getValue() : null;
    }

    @Override
    public Review save(Review review) {
        Review updated = getOneById(review.getId());
        return updated != null ? updateItem(updated, review) : insertItem(review);
    }

    @Override
    public void deleteById(UUID id) {
        Review finded = getOneById(id);
        if (finded != null)
            collection.remove(finded);
    }

    private Review insertItem(Review newReview) {
        UUID id = UUID.randomUUID();
        Review review = new Review(id, newReview.getComment());
        collection.put(id, review);
        return review;
    }

    private Review updateItem(Review oldReview, Review newReview) {
        collection.put(oldReview.getId(), newReview);
        return newReview;
    }
}
