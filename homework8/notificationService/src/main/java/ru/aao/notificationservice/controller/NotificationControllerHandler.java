package ru.aao.notificationservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.aao.notificationservice.exception.ApplicationException;

@Slf4j
@RestControllerAdvice
public class NotificationControllerHandler {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> handler(ApplicationException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body("Не удалось выполнить");
    }
}
