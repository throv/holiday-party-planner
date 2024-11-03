package com.ada.holiday_party_planning.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "item_Id", nullable = false)
    private UUID itemId;

    @Column(name ="name", nullable = false)
    private String name;

    @Column(name="quantity", nullable = false)
    private int quantity;

    @Column(name= "value", nullable = false)
    private double value;


    @Column(name = "event_id", nullable = false)
    private UUID eventId;

    public Item() {
    }

    public Item(UUID itemId, String name, int quantity, double value, UUID eventId) {
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

