package ru.aao.userservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import ru.aao.userservice.exception.ApplicationException;
import ru.aao.userservice.exception.AuthenticationException;
import ru.aao.userservice.model.dto.UserDto;
import ru.aao.userservice.service.UserService;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/users/me")
    public UserDto getMe(HttpServletRequest request) {
        val headerUserId = request.getHeader("X-User-Id");
        securityValidation(headerUserId);
        val userId = arithmeticValidation(headerUserId);
        return service.getUserById(userId);
    }

    @PostMapping("/users/me")
    public UserDto setMe(HttpServletRequest request) {
        val headerUserId = request.getHeader("X-User-Id");
        securityValidation(headerUserId);
        val id = arithmeticValidation(headerUserId);
        UserDto userDto = service.getUserById(id);
        userDto.setFirstName(request.getHeader("X-First-Name"));
        userDto.setLastName(request.getHeader("X-Last-Name"));
        userDto.setEmail(request.getHeader("X-Email"));
        userDto.setAge(userDto.getAge());
        service.update(userDto);
        return userDto;
    }

    @PostMapping("/users/{userId}")
    public UserDto setUser(HttpServletRequest request, @PathVariable long userId) {
        val headerUserId = request.getHeader("X-User-Id");
        securityValidation(headerUserId);
        val id = arithmeticValidation(headerUserId);
        if (id != userId) {
            throw new AuthenticationException("not valid userId");
        }
        UserDto userDto = service.getUserById(id);
        userDto.setFirstName(request.getHeader("X-First-Name"));
        userDto.setLastName(request.getHeader("X-Last-Name"));
        userDto.setEmail(request.getHeader("X-Email"));
        userDto.setAge(userDto.getAge());
        service.update(userDto);

        return userDto;
    }

    @PutMapping("/users/me")
    public UserDto updateMe(HttpServletRequest request) {
        val headerUserId = request.getHeader("X-User-Id");
        securityValidation(headerUserId);
        val id = arithmeticValidation(headerUserId);
        UserDto userDto = service.getUserById(id);
        userDto.setFirstName(request.getHeader("X-First-Name"));
        userDto.setLastName(request.getHeader("X-Last-Name"));
        userDto.setEmail(request.getHeader("X-Email"));
        userDto.setAge(userDto.getAge());
        service.update(userDto);

        return userDto;
    }

    @PutMapping("/users/{userId}")
    public UserDto updateUser(HttpServletRequest request, @PathVariable long userId) {
        val headerUserId = request.getHeader("X-User-Id");
        securityValidation(headerUserId);
        val id = arithmeticValidation(headerUserId);
        if (id != userId) {
            throw new AuthenticationException("not valid userId");
        }
        UserDto userDto = service.getUserById(id);
        userDto.setFirstName(request.getHeader("X-First-Name"));
        userDto.setLastName(request.getHeader("X-Last-Name"));
        userDto.setEmail(request.getHeader("X-Email"));
        userDto.setAge(userDto.getAge());
        service.update(userDto);

        return userDto;
    }

    private long arithmeticValidation(String userId) {
        try {
            return Long.parseLong(userId);
        }
        catch (Exception e) {
            throw  new ApplicationException("not valid user id");
        }
    }

    private static void securityValidation(String userId) {
        if (Objects.isNull(userId)) {
            throw new AuthenticationException("not valid authentication");
        }
    }
}
