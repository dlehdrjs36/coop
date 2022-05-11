package com.projectteam.coop.exception;

public class DuplicatePurchaseProductException extends RuntimeException {
    public DuplicatePurchaseProductException() {
        super();
    }

    public DuplicatePurchaseProductException(String message) {
        super(message);
    }

    public DuplicatePurchaseProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatePurchaseProductException(Throwable cause) {
        super(cause);
    }

    protected DuplicatePurchaseProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
