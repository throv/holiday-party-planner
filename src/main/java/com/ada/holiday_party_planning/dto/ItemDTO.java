package com.ada.holiday_party_planning.dto;

import java.util.UUID;

public class ItemDTO {
    private UUID itemId;
    private String name;
    private int quantity;
    private double value;

    public ItemDTO(UUID itemId, String name, int quantity, double value) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.value = value;
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
}
