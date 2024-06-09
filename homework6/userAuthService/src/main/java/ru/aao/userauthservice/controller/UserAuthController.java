package ru.aao.userauthservice.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import ru.aao.userauthservice.exception.AuthenticationException;
import ru.aao.userauthservice.model.dto.LoginDto;
import ru.aao.userauthservice.model.dto.UserDto;
import ru.aao.userauthservice.service.UserAuthService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService service;
    private final HashMap<UUID, UserDto> sessions = new HashMap<>();

    @GetMapping("/sessions")
    public Map<UUID, UserDto> getSessions() {
        return sessions;
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody UserDto userDto) {
        return service.create(userDto);
    }

    @PostMapping("/login")
    public String login(HttpServletResponse response, @RequestBody LoginDto loginDto) {
        val user = service.findByLoginPassword(loginDto.getLogin(), loginDto.getPassword());
        if (user.isPresent()) {
            UUID sessionId = UUID.randomUUID();
            sessions.put(sessionId, user.get());
            response.addCookie(new Cookie("session_id", sessionId.toString()));
            return "Login success";
        }
        else {
            throw new AuthenticationException("not found user");
        }
    }

    @GetMapping("/signin")
    public String signinGet() {
        return "Please login";
    }

    @PostMapping("/signin")
    public String signinPost() {
        return "Please login";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        Arrays.stream(cookies).filter(c -> c.getName().equals("session_id")).findFirst().ifPresent(cookie -> sessions.remove(UUID.fromString(cookie.getValue())));
        response.addCookie(new Cookie("session_id", null));
        return "user logout";
    }

    @PostMapping("/auth")
    public String auth(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("session_id")) {
                    if (!cookie.getValue().isBlank() && sessions.containsKey(UUID.fromString(cookie.getValue()))) {
                        UserDto userDto = sessions.get(UUID.fromString(cookie.getValue()));
                        response.setHeader("X-User-Id", String.valueOf(userDto.getId()));
                        response.setHeader("X-User", userDto.getLogin());
                        response.setHeader("X-Email", userDto.getEmail());
                        response.setHeader("X-First-Name", userDto.getFirstName());
                        response.setHeader("X-Last-Name", userDto.getLastName());
                        return "user authenticate";
                    }
                }
            }
        }
        throw new AuthenticationException("Error authenticating user");
    }
}
