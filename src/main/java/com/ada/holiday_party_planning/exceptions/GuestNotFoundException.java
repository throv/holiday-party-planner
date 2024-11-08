package com.ada.holiday_party_planning.exceptions;

public class GuestNotFoundException extends RuntimeException {

    public GuestNotFoundException() {
        super("Guest not found.");
    }
    public GuestNotFoundException(String message) {
        super(message);
    }
}
