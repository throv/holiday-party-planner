package com.ada.holiday_party_planning.service;

import com.ada.holiday_party_planning.exceptions.PartyOwnerNotFoundException;
import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.model.PartyOwner;
import com.ada.holiday_party_planning.repository.EventRepository;
import com.ada.holiday_party_planning.repository.PartyOwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final PartyOwnerRepository partyOwnerRepository;

    public EventService(EventRepository eventRepository, PartyOwnerRepository partyOwnerRepository) {
        this.eventRepository = eventRepository;
        this.partyOwnerRepository = partyOwnerRepository;
    }

    public Event createEvent(UUID ownerId, Event event) {
        PartyOwner partyOwner = partyOwnerRepository.findById(ownerId)
                .orElseThrow(PartyOwnerNotFoundException::new);
        event.setOwner(partyOwner);
        return eventRepository.save(event);
    }

    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> listAllEvent () {
        return eventRepository.findAll();
    }

    public List<Event> eventsByPartyOwner(UUID ownerID) {
        List<Event> allEvents = listAllEvent();
        allEvents.removeIf(event -> !ownerID.equals(event.getOwner().getOwnerId()));
        return allEvents;
    }

    public void deleteEvent (UUID eventID) {
        eventRepository.deleteById(eventID);
    }





}
