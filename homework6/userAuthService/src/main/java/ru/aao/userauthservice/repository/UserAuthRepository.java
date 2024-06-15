package ru.aao.userauthservice.repository;

import ru.aao.userauthservice.model.dto.UserDto;

import java.util.Optional;

public interface UserAuthRepository {
    Optional<UserDto> findByLoginPassword(String login, String password);
    UserDto save(UserDto user);
    void update(UserDto user);
}
