package ru.aao.userservice.service;

import ru.aao.userservice.model.dto.UserDto;

public interface UserService {
    UserDto getUserById(long id);
    Boolean save(long id, String login);
    Boolean update(UserDto user);
}
