package web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import web.domain.Author;

public interface AuthorRepo extends JpaRepository<Author, Long> {
}
