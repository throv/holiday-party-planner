package com.ada.holiday_party_planning.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super("Email already exists.");
    }
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
