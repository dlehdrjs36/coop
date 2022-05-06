package com.projectteam.coop.exception;

public class MisMatchedPasswordException extends RuntimeException {
    public MisMatchedPasswordException() {
        super();
    }

    public MisMatchedPasswordException(String message) {
        super(message);
    }

    public MisMatchedPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public MisMatchedPasswordException(Throwable cause) {
        super(cause);
    }

    protected MisMatchedPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
