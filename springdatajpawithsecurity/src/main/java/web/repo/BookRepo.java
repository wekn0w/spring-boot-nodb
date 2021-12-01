package web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import web.domain.Book;

public interface BookRepo extends JpaRepository<Book, Long> {
}
