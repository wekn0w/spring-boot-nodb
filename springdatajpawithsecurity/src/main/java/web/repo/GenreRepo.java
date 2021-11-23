package web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import web.domain.Genre;

public interface GenreRepo extends JpaRepository<Genre, Long> {
}
