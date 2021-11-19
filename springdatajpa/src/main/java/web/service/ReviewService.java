package web.service;

import web.dto.ReviewDto;

import javax.transaction.Transactional;
import java.util.List;

public interface ReviewService {
    List<ReviewDto> findAll();

    ReviewDto getOneById(Long id);

    @Transactional
    ReviewDto save(ReviewDto person);

    @Transactional
    void deleteById(Long id);
}
