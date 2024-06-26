package ru.aao.eventservice.common.exception;

public class DBException extends ApplicationException {
    public DBException(int errCode, String description, String system) {
        super(errCode + " :: " + description + " :: " + system);
    }

    public DBException(Throwable cause) {
        super(cause);
    }
}
