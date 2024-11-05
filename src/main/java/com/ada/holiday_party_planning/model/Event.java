package com.ada.holiday_party_planning.model;

import com.ada.holiday_party_planning.enums.GuestStatusEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "events_planned")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "event_id")
    private UUID eventId;

    private String theme;

    private String title;

    private LocalDateTime date;

    private String place;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "owner_id", nullable = false)
    @JsonIgnore
    private PartyOwner partyOwner;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Guest> guests;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Item> items;

    public Event() {
    }

    public Event(String theme, String title, LocalDateTime date, String place, PartyOwner partyOwner) {
        this.theme = theme;
        this.title = title;
        this.date = date;
        this.place = place;
        this.partyOwner = partyOwner;
    }

    public UUID getEventId() {
        return eventId;
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


    public PartyOwner getOwner() {
        return partyOwner;
    }

    public void setOwner(PartyOwner partyOwner) {
        this.partyOwner = partyOwner;
    }

    public void addGuest(Guest guest) {
        guests.add(guest);
        guest.setEvent(this);
    }

    public void addItem(Item item) {
        items.add(item);
        item.setEvent(this);
    }

    public void deleteGuest(Guest guest) {
        guests.remove(guest);
        guest.setEvent(null);
    }

    public void deleteItem(Item item) {
        items.remove(item);
        item.setEvent(null);
    }

    public double totalEventCost() {
        return items
                .stream()
                .mapToDouble(item -> item.getQuantity() * item.getValue()).sum();
    }

    public List<Guest> confirmedGuests() {
        return guests
                .stream()
                .filter(guest -> guest.getStatus() == GuestStatusEnum.CONFIRMED)
                .toList();
    }

    public List<Guest> notConfirmedGuests() {
        return guests
                .stream()
                .filter(guest -> guest.getStatus() == GuestStatusEnum.DECLINED)
                .toList();
    }
}
