package web.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import web.domain.User;
import web.dto.UserDto;
import web.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;

    public UserServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto findUserByEmail(String username) {
        User finded = userRepository.findUserByEmail(username);
        return finded != null ? new UserDto(finded.getId(), finded.getEmail(), finded.getPassword(), finded.getRole()) : null;
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> resultList = new ArrayList<>();
        for (User record : users) {
            resultList.add(new UserDto(record.getId(), record.getEmail(), record.getPassword(), record.getRole()));
        }
        return resultList;
    }

    @Override
    public UserDto save(UserDto user) {
        User newUser = userRepository.findById(user.getId()).orElse(new User());
        if (user.getRole() != null) {
            newUser.setRole(user.getRole());
        }
        if (user.getEmail() != null && !user.getEmail().isBlank()) {
            newUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            newUser.setPassword(encodePassword(user.getPassword()));
//            newUser.setPassword(user.getPassword());
        }
        User saved = userRepository.save(newUser);
        return new UserDto(saved.getId(), saved.getEmail(), saved.getPassword(), saved.getRole());
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
