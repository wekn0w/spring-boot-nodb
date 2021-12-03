package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import web.domain.Permission;
import web.domain.Role;
import web.domain.User;
import web.repo.RoleRepo;
import web.repo.UserRepo;

import javax.annotation.PostConstruct;
import java.util.Set;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired//todo понюхать
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @PostConstruct
    public void init() {
        Role admin = new Role("admin");
        admin.setPermissions(Set.of(Permission.RECORD_READ.getPermission(), Permission.RECORD_WRITE.getPermission()));
        roleRepo.save(admin);
        Role user = new Role("user");
        admin.setPermissions(Set.of(Permission.RECORD_READ.getPermission()));
        roleRepo.save(user);

        User user1 = new User();
        user1.setEmail("qwe@local");
        user1.setPassword(new BCryptPasswordEncoder().encode("123456"));
        //user1.setPassword("123456");
        user1.setRole(user);
        userRepo.save(user1);

        User user2 = new User();
        user2.setEmail("admin@local");
        user2.setPassword(new BCryptPasswordEncoder().encode("admin1"));
        //user2.setPassword("admin1");
        user2.setRole(admin);
        userRepo.save(user2);
    }
}
