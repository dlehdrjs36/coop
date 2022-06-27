package com.projectteam.coop.exception;

public class NoAuthenticationException extends RuntimeException {
    public NoAuthenticationException() {
        super();
    }

    public NoAuthenticationException(String message) {
        super(message);
    }

    public NoAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAuthenticationException(Throwable cause) {
        super(cause);
    }

    protected NoAuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
