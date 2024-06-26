package ru.aao.orderservice.exception;

public class NotValidIdempotentException extends ApplicationException {

    public NotValidIdempotentException(String e) {
        super(e);
    }
}
