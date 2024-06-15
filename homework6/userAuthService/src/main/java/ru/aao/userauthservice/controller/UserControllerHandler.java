package ru.aao. userauthservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.aao.userauthservice.exception.ApplicationException;
import ru.aao.userauthservice.exception.AuthenticationException;

@Slf4j
@RestControllerAdvice
public class UserControllerHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> handler(ApplicationException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body("Ошибка параметров");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handler(AuthenticationException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(401).contentType(MediaType.APPLICATION_JSON).body("Ошибка аутентификации");
    }
}
