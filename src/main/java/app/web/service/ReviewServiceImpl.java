package app.web.service;

import app.web.domain.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final List<Review> collection = new ArrayList<>();

    @Override
    public List<Review> findAll() {
        return collection;
    }

    @Override
    public Review getOneById(long id) {
        Review finded = null;
        for (Review item : collection) {
            if (id == (item.getId())) {
                finded = item;
                break;
            }
        }
        return finded;
    }

    @Override
    public Review save(Review review) {
        Review updated = getOneById(review.getId());
        return updated != null ? updateItem(updated, review) : insertItem(review);
    }

    private Review insertItem(Review newReview) {
        collection.add(newReview);
        return newReview;
    }

    private Review updateItem(Review oldReview, Review newReview) {
        oldReview.setComment(newReview.getComment());
        return oldReview;
    }

    @Override
    public void deleteById(long id) {
        Review finded = getOneById(id);
        if (finded != null)
            collection.remove(finded);
    }
}
