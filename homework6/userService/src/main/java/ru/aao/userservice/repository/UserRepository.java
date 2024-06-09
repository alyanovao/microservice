package ru.aao.userservice.repository;

import ru.aao.userservice.model.dao.User;
import ru.aao.userservice.model.dto.UserDto;

import java.util.Optional;

public interface UserRepository {
    Optional<UserDto> findById(long id);
    User save(long id, String login);

    void update(UserDto user);
}
