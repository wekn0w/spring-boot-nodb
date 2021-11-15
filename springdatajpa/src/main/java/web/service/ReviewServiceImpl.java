package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.domain.Review;
import web.repo.ReviewRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepo reviewRepository;

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getOneById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Review save(Review review) {
        Review newReview = reviewRepository.findById(review.getId()).orElse(new Review());
        if (review.getComment() != null && !review.getComment().isEmpty()) {
            newReview.setComment(review.getComment());
        }
        return reviewRepository.save(newReview);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}
