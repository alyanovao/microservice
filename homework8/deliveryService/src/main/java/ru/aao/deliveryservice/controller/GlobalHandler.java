package ru.aao.deliveryservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.aao.deliveryservice.dto.ResponseError;
import ru.aao.deliveryservice.exception.ApplicationException;

@RestControllerAdvice
public class GlobalHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ApplicationException.class)
    public ResponseError handler(ApplicationException exception) {
        return ResponseError.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .build();
    }
}
