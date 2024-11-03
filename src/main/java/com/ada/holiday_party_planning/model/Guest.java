package com.ada.holiday_party_planning.model;

import com.ada.holiday_party_planning.enums.GuestStatusEnum;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "guests")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "guest_id", nullable = false)
    private UUID guestId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private GuestStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id", nullable = false)
    private Event event;

    public Guest() {}

    public Guest(UUID guestId, String name, String email, GuestStatusEnum status, Event event) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
        this.status = status;
        this.event = event;
    }

    public UUID getGuestId() {
        return guestId;
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
        status = status;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
