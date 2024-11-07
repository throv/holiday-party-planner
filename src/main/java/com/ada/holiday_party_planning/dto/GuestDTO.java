package com.ada.holiday_party_planning.dto;

import com.ada.holiday_party_planning.enums.GuestStatusEnum;

import java.util.UUID;

public class GuestDTO {

    private UUID guestId;
    private String name;
    private String email;
    private GuestStatusEnum status;

    public GuestDTO(UUID guestId, String name, String email, GuestStatusEnum status) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public UUID getGuestId() {
        return guestId;
    }

    public void setGuestId(UUID guestId) {
        this.guestId = guestId;
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

    public GuestStatusEnum getStatus() {
        return status;
    }

    public void setStatus(GuestStatusEnum status) {
        this.status = status;
    }
}
