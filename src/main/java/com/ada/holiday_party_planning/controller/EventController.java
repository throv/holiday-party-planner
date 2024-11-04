package com.ada.holiday_party_planning.controller;

import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.model.PartyOwner;
import com.ada.holiday_party_planning.repository.EventRepository;
import com.ada.holiday_party_planning.repository.PartyOwnerRepository;
import com.ada.holiday_party_planning.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping ("/all")
    public List<Event> findAllEvent (){
        return eventService.listAllEvent();
    }

    @GetMapping ("/{ownerId}/listEvent")
    public List<Event> findByEventOwner (@PathVariable UUID ownerID) {
        return eventService.eventsByPartyOwner(ownerID);
    }

    @PostMapping("/{ownerId}/create")
    public Event createEvent(@PathVariable UUID ownerId, @RequestBody Event event) {
        return eventService.createEvent(ownerId,event);
    }

    @PutMapping ("/update")
    public Event updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }

    @DeleteMapping ("/{id}")
    public void deleteEvent(@PathVariable UUID id) {
        eventService.deleteEvent(id);
    }



}
