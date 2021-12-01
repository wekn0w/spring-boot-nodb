package web.service;

import web.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto findUserByEmail(String username);

    List<UserDto> findAll();

    UserDto save(UserDto user);
}
