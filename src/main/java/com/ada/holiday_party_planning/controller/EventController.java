package com.ada.holiday_party_planning.controller;

import com.ada.holiday_party_planning.dto.CreateEventDTO;
import com.ada.holiday_party_planning.dto.EventWithPartyOwnerDTO;
import com.ada.holiday_party_planning.dto.UpdateEventDTO;
import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> findAllEvent() {
        List<Event> events = eventService.listAllEvent();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{ownerId}/listevent")
    public ResponseEntity<List<EventWithPartyOwnerDTO>> findByEventOwner(@PathVariable UUID ownerId) {
        List<EventWithPartyOwnerDTO> events = eventService.eventsByPartyOwner(ownerId);
        return ResponseEntity.ok(events);
    }

    @PostMapping("/{ownerId}/create")
    public ResponseEntity<Void> createEvent(@PathVariable UUID ownerId, @RequestBody CreateEventDTO event) {
        eventService.createEvent(ownerId, event);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{eventId}/update")
    public ResponseEntity<Void> updateEvent(@PathVariable UUID eventId, @Valid @RequestBody UpdateEventDTO updateEventDTO) {
        eventService.updateEvent(eventId, updateEventDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }



}
