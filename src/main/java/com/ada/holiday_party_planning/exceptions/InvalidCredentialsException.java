package com.ada.holiday_party_planning.exceptions;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {
        super("Email or password is not valid.");
    }
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
