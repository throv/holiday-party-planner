package com.ada.holiday_party_planning.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventWithPartyOwnerDTO {

    private final UUID eventID;
    private String theme;
    private String title;
    private LocalDateTime date;
    private String place;
    private PartyOwnerDTO partyOwnerDTO;

    public EventWithPartyOwnerDTO(UUID eventID, String theme, String title, LocalDateTime date,
                                  String place, PartyOwnerDTO partyOwnerDTO) {
        this.eventID = eventID;
        this.theme = theme;
        this.title = title;
        this.date = date;
        this.place = place;
        this.partyOwnerDTO = partyOwnerDTO;
    }

    public EventWithPartyOwnerDTO(UUID eventID, String theme, String title, LocalDateTime date,
                                  String place) {
        this.eventID = eventID;
        this.theme = theme;
        this.title = title;
        this.date = date;
        this.place = place;
    }

    public UUID getEventID() {
        return eventID;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public PartyOwnerDTO getPartyOwnerDTO() {
        return partyOwnerDTO;
    }

    public void setPartyOwnerDTO(PartyOwnerDTO partyOwnerDTO) {
        this.partyOwnerDTO = partyOwnerDTO;
    }

}
