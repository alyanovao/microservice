package ru.aao.usersapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.aao.api.UserApi;
import ru.aao.api.model.User;
import ru.aao.usersapi.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService service;

    @Override
    public ResponseEntity<User> findUserById(Long userId) {
        val user = service.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Void> createUser(User user) {
        service.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> updateUser(Long userId, User user) {
        service.update(userId, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        service.delete(userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
