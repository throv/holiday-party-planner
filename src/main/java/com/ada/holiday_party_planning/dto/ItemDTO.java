package com.ada.holiday_party_planning.dto;

import com.ada.holiday_party_planning.model.Event;
import jakarta.persistence.*;

import java.util.UUID;

public class ItemDTO {

    private UUID itemId;
    private String name;
    private int quantity;
    private double value;
    private UUID eventId;

    public ItemDTO(UUID itemId, String name, int quantity, double value, UUID eventId) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.value = value;
        this.eventId = eventId;
    }

    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }
}

