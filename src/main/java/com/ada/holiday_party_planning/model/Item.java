package com.ada.holiday_party_planning.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "item_Id")
    private UUID itemId;
}
