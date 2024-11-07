package com.ada.holiday_party_planning.exceptions;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException() {
        super("Item not found.");
    }

}
