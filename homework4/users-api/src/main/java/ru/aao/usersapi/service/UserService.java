package ru.aao.usersapi.service;

import ru.aao.api.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    List<User> getAll();
    Boolean save(User user);
    Boolean update(Long id, User user);
    Boolean delete(Long id);
}
