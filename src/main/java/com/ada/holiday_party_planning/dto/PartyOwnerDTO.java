package com.ada.holiday_party_planning.dto;

import java.util.UUID;

public class PartyOwnerDTO {

    private final UUID ownerId;
    private String name;
    private String email;

    public PartyOwnerDTO(UUID ownerId, String name, String email) {
        this.ownerId = ownerId;
        this.name = name;
        this.email = email;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
