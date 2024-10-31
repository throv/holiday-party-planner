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

    @Column(name= "value", nullable = false )
    private double value;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "item_id", nullable = false)
            private Event eventId;
}
