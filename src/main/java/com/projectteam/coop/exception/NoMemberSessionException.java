package com.projectteam.coop.exception;

public class NoMemberSessionException extends RuntimeException {
    public NoMemberSessionException() {
        super();
    }

    public NoMemberSessionException(String message) {
        super(message);
    }

    public NoMemberSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMemberSessionException(Throwable cause) {
        super(cause);
    }

    protected NoMemberSessionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
