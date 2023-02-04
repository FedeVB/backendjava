package com.backendjava.app.exceptions;

public class PublicationNotFoundException extends RuntimeException {
    public PublicationNotFoundException() {
    }

    public PublicationNotFoundException(String message) {
        super(message);
    }

    public PublicationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
