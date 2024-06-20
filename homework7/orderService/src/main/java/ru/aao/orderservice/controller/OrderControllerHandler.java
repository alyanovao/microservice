package ru.aao.orderservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.aao.orderservice.exception.ApplicationException;
import ru.aao.orderservice.exception.Error;
import ru.aao.orderservice.exception.NoAmountAmountException;

@Slf4j
@RestControllerAdvice
public class OrderControllerHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Error> handler(ApplicationException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(new Error("Не удалось выполнить"));
    }

    @ExceptionHandler(NoAmountAmountException.class)
    public ResponseEntity<Error> handler(NoAmountAmountException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(new Error(e.getMessage()));
    }
}
