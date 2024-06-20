package ru.aao.billingservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.aao.billingservice.exception.ApplicationException;
import ru.aao.billingservice.exception.Error;
import ru.aao.billingservice.exception.NoAmountAmountException;

@Slf4j
@RestControllerAdvice
public class BillingServiceHandler {

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
