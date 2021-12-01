package web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import web.domain.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
