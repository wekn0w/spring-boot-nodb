package web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import web.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {

    User findUserByEmail(String login);
}
