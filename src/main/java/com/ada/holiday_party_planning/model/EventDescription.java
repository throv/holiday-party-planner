package com.ada.holiday_party_planning.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "events_description")
public class EventDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "event_description_id")
    private UUID eventDescriptionID;

    private String mensage;

    private String mensageTranslateFun;

    private String category; // minion or hodor

    private Boolean fun_activate;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id", nullable = false)
    @JsonIgnore
    private Event event;

    public EventDescription() {
    }

    public EventDescription(String mensage,
                            String mensageTranslateFun, String category,
                            Boolean fun_activate, Event event) {
        this.mensage = mensage;
        this.mensageTranslateFun = mensageTranslateFun;
        this.category = category;
        this.fun_activate = fun_activate;
        this.event = event;
    }

    public UUID getEventDescriptionID() {
        return eventDescriptionID;
    }

    public void setEventDescriptionID(UUID eventDescriptionID) {
        this.eventDescriptionID = eventDescriptionID;
    }

    public String getMensage() {
        return mensage;
    }

    public void setMensage(String mensage) {
        this.mensage = mensage;
    }

    public String getMensageTranslateFun() {
        return mensageTranslateFun;
    }

    public void setMensageTranslateFun(String mensageTranslateFun) {
        this.mensageTranslateFun = mensageTranslateFun;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getFun_activate() {
        return fun_activate;
    }

    public void setFun_activate(Boolean fun_activate) {
        this.fun_activate = fun_activate;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}
