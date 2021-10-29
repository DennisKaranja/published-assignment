package com.carepay.assignment.exception;

public class RequestException extends RuntimeException{
    public RequestException(final String message) {
        super(message);
    }

    public RequestException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
