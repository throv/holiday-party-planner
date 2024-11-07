package com.ada.holiday_party_planning.controller;

import com.ada.holiday_party_planning.dto.*;
import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.service.EmailService;
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
    private final EmailService emailService;

    public EventController(EventService eventService, EmailService emailService) {
        this.eventService = eventService;
        this.emailService = emailService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> findAllEvent() {
        List<Event> events = eventService.listAllEvent();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{ownerId}/list")
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

    @GetMapping("/{eventId}/send-email")
    public ResponseEntity<String> sendEmail(@PathVariable UUID eventId, @RequestParam String email) {

        String eventLink = "http://localhost:8080/events/" + eventId;
        emailService.sendEmail(email, "Event Link", eventLink);
        return ResponseEntity.ok().build();
    }

}
