package ru.aao.userservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.aao.userservice.exception.ApplicationException;
import ru.aao.userservice.exception.AuthenticationException;

@Slf4j
@RestControllerAdvice
public class UserControllerHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> handler(ApplicationException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body("Ошибка параметров");
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handler(EmptyResultDataAccessException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(503).contentType(MediaType.APPLICATION_JSON).body("Не найден клиент");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handler(AuthenticationException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(401).contentType(MediaType.APPLICATION_JSON).body("Ошибка аутентификации");
    }
}
