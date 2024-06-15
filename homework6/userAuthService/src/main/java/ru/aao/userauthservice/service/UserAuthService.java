package ru.aao.userauthservice.service;

import ru.aao.userauthservice.model.dto.UserDto;

import java.util.Optional;

public interface UserAuthService {
    Optional<UserDto> findByLoginPassword(String login, String password);
    UserDto create(UserDto user);
}
