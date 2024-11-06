package com.ada.holiday_party_planning.exceptions;

public class PartyOwnerNotFoundException extends RuntimeException {

    public PartyOwnerNotFoundException() {
        super("No Party Owners found.");
    }
    public PartyOwnerNotFoundException(String message) {
        super(message);
    }
}
