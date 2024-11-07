package com.ada.holiday_party_planning.service;

import com.ada.holiday_party_planning.dto.CreateEventDTO;
import com.ada.holiday_party_planning.dto.EventWithPartyOwnerDTO;
import com.ada.holiday_party_planning.dto.UpdateEventDTO;
import com.ada.holiday_party_planning.mappers.EventMapper;
import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.model.PartyOwner;
import com.ada.holiday_party_planning.repository.EventRepository;
import com.ada.holiday_party_planning.repository.PartyOwnerRepository;
import com.ada.holiday_party_planning.util.APIFunTranlation;
import com.ada.holiday_party_planning.util.APIGoogleTranslate;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final PartyOwnerRepository partyOwnerRepository;

    public EventService(EventRepository eventRepository, PartyOwnerRepository partyOwnerRepository) {
        this.eventRepository = eventRepository;
        this.partyOwnerRepository = partyOwnerRepository;
    }

    public void createEvent(UUID ownerID, CreateEventDTO createEventDTO) {
        EventMapper eventMapper = new EventMapper(partyOwnerRepository,eventRepository);
        PartyOwner partyOwner = partyOwnerRepository.findById(ownerID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PartyOwner not found"));
        Event event =  eventMapper.createDTOToModel(createEventDTO,partyOwner);
        eventRepository.save(event);
        translateFun(event.getEventId());
    }

    public void updateEvent(UUID eventId, UpdateEventDTO updateEventDTO) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        event.setTheme(updateEventDTO.getTheme());
        event.setTitle(updateEventDTO.getTitle());
        event.setDate(updateEventDTO.getDate());
        event.setPlace(updateEventDTO.getPlace());
        eventRepository.save(event);
        translateFun(eventId);
    }

    public List<Event> listAllEvent () {
        return eventRepository.findAll();
    }

    public List<EventWithPartyOwnerDTO> eventsByPartyOwner(UUID ownerID) {
        EventMapper eventMapper = new EventMapper(partyOwnerRepository,eventRepository);
        List<EventWithPartyOwnerDTO> allEvents = eventMapper.eventWithPartyOwnerDTO(ownerID);
        return allEvents;
    }

    public void deleteEvent (UUID eventID) {
        eventRepository.deleteById(eventID);
    }

    @Async
    public void translateFun (UUID eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        if (event.getFunActivate() && event.getDescription() != null) {
            try {
                String textTranslate = APIGoogleTranslate.translateMensage(event.getDescription(), "pt-br", "en");
                // String textFun = APIFunTranlation.tranlateFun(textTranslate, event.getCategoryFun());
                event.setDescriptionTranslateFun("050");
                System.out.println(textTranslate);
            } catch (Exception e) {
                System.out.println("Erro na tradução: " + e.getMessage());
            }
        }
    }

}
