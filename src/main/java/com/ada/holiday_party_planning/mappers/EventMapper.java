package com.ada.holiday_party_planning.mappers;

import com.ada.holiday_party_planning.dto.*;
import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.model.PartyOwner;
import com.ada.holiday_party_planning.repository.EventRepository;
import com.ada.holiday_party_planning.repository.PartyOwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Classe responsável por realizar a conversão entre objetos DTO (Data Transfer Object) e modelos
 * relacionados a eventos e proprietários de festas, utilizando os repositórios de PartyOwner e Event.
 * Ela facilita a transferência de dados entre as camadas de controle e serviço da aplicação.
 *
 * Além disso, ela fornece métodos para listar eventos com informações dos proprietários de festas e
 * para criar novos eventos com os dados fornecidos.
 */

public class EventMapper {

    private final PartyOwnerRepository partyOwnerRepository;
    private final EventRepository eventRepository;

    /**
     * Construtor que inicializa o EventMapper com os repositórios de PartyOwner e Event.
     *
     * @param partyOwnerRepository O repositório de proprietários de festas.
     * @param eventRepository O repositório de eventos.
     */

    public EventMapper(PartyOwnerRepository partyOwnerRepository,
                       EventRepository eventRepository) {
        this.partyOwnerRepository = partyOwnerRepository;
        this.eventRepository = eventRepository;
    }

    /**
     * Converte um evento e um DTO de PartyOwner para um DTO de evento com informações completas do PartyOwner.
     *
     * @param event O evento a ser convertido.
     * @param partyOwner O DTO do proprietário do evento.
     * @return Um objeto EventWithPartyOwnerDTO representando o evento com informações do PartyOwner.
     */

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

    /**
     * Retorna uma lista de eventos com informações de um proprietário de festa específico.
     *
     * @param ownerID O ID do proprietário da festa.
     * @return Uma lista de EventWithPartyOwnerDTO contendo os eventos associados ao proprietário.
     */

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

    /**
     * Retorna uma lista de eventos de um único usuário em formato DTO.
     *
     * @param ownerID O ID do proprietário da festa.
     * @return Uma lista de EventDTO representando os eventos do usuário.
     */

    public List<EventDTO> eventListDTO (UUID ownerID) {
        List<Event> allEvents = eventRepository.findAll();
        List<EventDTO> allEventsDTO = new ArrayList<>();
        allEvents.removeIf(event -> !ownerID.equals(event.getOwner().getOwnerId()));

        for (Event event : allEvents) {
            allEventsDTO.add(new EventDTO(
                    event.getEventId(), event.getTheme(),
                    event.getTitle(), event.getDate(),
                    event.getPlace(), event.getDescription(),
                    event.getDescriptionTranslateFun(),
                    event.getFunActivate(),
                    event.getCategoryFun())
            );
        }

        return allEventsDTO;
    }

    /**
     * Converte um evento e o ID do proprietário para um DTO de criação de evento.
     *
     * @param event O evento a ser convertido.
     * @param ownerID O ID do proprietário do evento.
     * @return Um CreateEventDTO representando os dados necessários para criar um evento.
     */

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

    /**
     * Converte um DTO de criação de evento para um modelo de evento.
     *
     * @param createEventDTO O DTO com os dados do evento.
     * @param partyOwner O proprietário do evento.
     * @return Um objeto Event representando o modelo de evento a ser salvo no banco de dados.
     */

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