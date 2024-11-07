package com.ada.holiday_party_planning.exceptions;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException() {
        super("No events found.");
    }
    public EventNotFoundException(String message) {
        super(message);
    }
}
