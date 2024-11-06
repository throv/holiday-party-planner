package com.ada.holiday_party_planning.mappers;

import com.ada.holiday_party_planning.dto.CreateEventDTO;
import com.ada.holiday_party_planning.dto.CreatePartyOwnerDTO;
import com.ada.holiday_party_planning.dto.EventWithPartyOwnerDTO;
import com.ada.holiday_party_planning.dto.PartyOwnerDTO;
import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.model.PartyOwner;
import com.ada.holiday_party_planning.repository.EventRepository;
import com.ada.holiday_party_planning.repository.PartyOwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventMapper {

    private final PartyOwnerRepository partyOwnerRepository;
    private final EventRepository eventRepository;

    public EventMapper(PartyOwnerRepository partyOwnerRepository,
                       EventRepository eventRepository) {
        this.partyOwnerRepository = partyOwnerRepository;
        this.eventRepository = eventRepository;
    }

    public EventWithPartyOwnerDTO eventwithPartyOwnerDTO (Event event, PartyOwnerDTO partyOwner) {
        return new EventWithPartyOwnerDTO(
                event.getEventId(), event.getTheme(),
                event.getTitle(), event.getDate(),
                event.getPlace(), event.getDescription(),
                event.getDescriptionTranslateFun(), event.getFunActivate(),
                event.getCategoryFun(),
                new PartyOwnerDTO(partyOwner.getOwnerId(),
                        partyOwner.getName(), partyOwner.getEmail())
        );
    }

    //LISTA TODOS OS EVENTOS COM PARTYOWNER
    public List<EventWithPartyOwnerDTO> eventWithPartyOwnerDTO(UUID ownerID) {
        PartyOwner partyOwner = partyOwnerRepository.findById(ownerID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PartyOwner not found"));

        PartyOwnerDTO partyOwnerDTO = new PartyOwnerDTO(partyOwner.getOwnerId(), partyOwner.getName(), partyOwner.getEmail());
        List<Event> allEvents = eventRepository.findAll();
        List<EventWithPartyOwnerDTO> allEventsDTO = new ArrayList<>();
        allEvents.removeIf(event -> !ownerID.equals(event.getOwner().getOwnerId()));

        for (Event event : allEvents) {
            allEventsDTO.add(new EventWithPartyOwnerDTO(
                    event.getEventId(), event.getTheme(),
                    event.getTitle(), event.getDate(),
                    event.getPlace(), event.getDescription(),
                    event.getDescriptionTranslateFun(), event.getFunActivate(),
                    event.getCategoryFun(),
                    partyOwnerDTO
            ));
        }

        return allEventsDTO;
    }

    //LISTA TODOS EVENTOS DE UM UNICO USARIO
    public List<EventWithPartyOwnerDTO> eventListDTO(UUID ownerID) {
        List<Event> allEvents = eventRepository.findAll();
        List<EventWithPartyOwnerDTO> allEventsDTO = new ArrayList<>();
        allEvents.removeIf(event -> !ownerID.equals(event.getOwner().getOwnerId()));

        for (Event event : allEvents) {
            allEventsDTO.add(new EventWithPartyOwnerDTO(
                    event.getEventId(), event.getTheme(),
                    event.getTitle(), event.getDate(),
                    event.getPlace(), event.getDescription(),
                    event.getDescriptionTranslateFun(), event.getFunActivate(),
                    event.getCategoryFun()));
        }
        return allEventsDTO;
    }

    public CreateEventDTO toCreateEventDTO(Event event, UUID ownerID) {
        PartyOwner partyOwner = partyOwnerRepository.findById(ownerID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PartyOwner not found"));

        PartyOwnerDTO partyOwnerDTO = new PartyOwnerDTO(
                partyOwner.getOwnerId(),
                partyOwner.getName(),
                partyOwner.getEmail()
        );

        return new CreateEventDTO(
                event.getTheme(),
                event.getTitle(), event.getDate(),
                event.getPlace(), event.getDescription(),
                event.getFunActivate(),
                event.getCategoryFun(),
                partyOwnerDTO
        );
    }

    public Event createDTOToModel(CreateEventDTO createEventDTO, PartyOwner partyOwner) {
        return new Event (
                createEventDTO.getTheme(),
                createEventDTO.getTitle(),
                createEventDTO.getDate(),
                createEventDTO.getPlace(),
                createEventDTO.getDescription(),
                createEventDTO.getFunActivate(),
                createEventDTO.getCategoryFun(),
                partyOwner
        );
    }



























}
