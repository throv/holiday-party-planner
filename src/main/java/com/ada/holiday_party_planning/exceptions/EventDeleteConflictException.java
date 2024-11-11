package com.ada.holiday_party_planning.exceptions;

public class EventDeleteConflictException extends RuntimeException {

    public EventDeleteConflictException() {
        super("Event cannot be deleted: there are guests already confirmed!");
    }

    public EventDeleteConflictException(String message) {
        super(message);
    }
}
