package ru.aao.usersapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.aao.usersapi.model.dto.Response;

@ControllerAdvice
public class UserAdvice {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Response> handleException(ApplicationException e) {
        Response res = new Response(e.getMessage());
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
