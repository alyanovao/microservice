package ru.aao.billingservice.exception;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String e) {
        super(e);
    }
}
