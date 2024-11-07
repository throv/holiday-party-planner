package com.ada.holiday_party_planning.dto;

import com.ada.holiday_party_planning.enums.GuestStatusEnum;
import com.ada.holiday_party_planning.model.Event;

public class CreateGuestDTO {

    private String name;
    private String email;
    private GuestStatusEnum status;
    private Event event;
    private boolean isConfirmed;

    public CreateGuestDTO(String name, String email, GuestStatusEnum status,Event event, boolean isConfirmed) {
        this.name = name;
        this.email = email;
        this.status = status;
        this.event = event;
        this.isConfirmed = isConfirmed;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

}
