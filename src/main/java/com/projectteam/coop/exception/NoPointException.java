package com.projectteam.coop.exception;

public class NoPointException  extends RuntimeException {
    public NoPointException() {
        super();
    }

    public NoPointException(String message) {
        super(message);
    }

    public NoPointException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPointException(Throwable cause) {
        super(cause);
    }

    protected NoPointException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
