package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.domain.Book;
import web.domain.Review;
import web.dto.ReviewDto;
import web.repo.BookRepo;
import web.repo.ReviewRepo;
import web.utils.BookAdapter;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepo reviewRepository;
    private BookRepo bookRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepo reviewRepository, BookRepo bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public List<ReviewDto> findAll() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewDto> resultList = new ArrayList<>();
        reviews.forEach(i -> resultList.add(new ReviewDto(i.getId(), i.getComment(), new BookAdapter().convertToDto(i.getBook()))));
        return resultList;
    }

    @Override
    public ReviewDto getOneById(Long id) {
        Review stored = reviewRepository.findById(id).orElse(new Review());
        return new ReviewDto(stored.getId(), stored.getComment());
    }

    @Override
    public ReviewDto save(ReviewDto review) {
        Review newReview = reviewRepository.findById(review.getId()).orElse(new Review());
        if (review.getComment() != null && !review.getComment().isEmpty()) {
            newReview.setComment(review.getComment());
        }
        if (newReview.getBook() == null && review.getBook() != null) {
            Book savedBook = bookRepository.findById(review.getBook().getId()).orElse(null);
            newReview.setBook(savedBook);
        }
        Review saved = reviewRepository.save(newReview);
        return new ReviewDto(saved.getId(), saved.getComment(), new BookAdapter().convertToDto(saved.getBook()));
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}
