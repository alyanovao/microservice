package ru.aao.orderservice.exception;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String e) {
        super(e);
    }

    public ApplicationException(Exception e) {
        super(e);
    }
}
