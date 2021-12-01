package web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import web.domain.Review;

public interface ReviewRepo extends JpaRepository<Review, Long> {
}
