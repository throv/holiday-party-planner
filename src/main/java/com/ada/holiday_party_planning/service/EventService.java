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

/**
 * Serviço para gerenciar eventos e a lógica de negócio relacionada aos eventos e seus donos.
 *
 * Esta classe oferece operações para criar, atualizar, listar, excluir eventos e realizar traduções assíncronas para os eventos.
 */

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final PartyOwnerRepository partyOwnerRepository;

    /**
     * Construtor do serviço que recebe os repositórios necessários.
     *
     * @param eventRepository Repositório para manipulação de eventos.
     * @param partyOwnerRepository Repositório para manipulação dos donos de festa.
     */

    public EventService(EventRepository eventRepository, PartyOwnerRepository partyOwnerRepository) {
        this.eventRepository = eventRepository;
        this.partyOwnerRepository = partyOwnerRepository;
    }

    /**
     * Cria um novo evento e o associa a um dono de festa.
     *
     * @param ownerID O ID do dono da festa.
     * @param createEventDTO O DTO contendo os dados para criação do evento.
     *
     * @throws ResponseStatusException Se o dono da festa não for encontrado.
     */

    public void createEvent(UUID ownerID, CreateEventDTO createEventDTO) {
        EventMapper eventMapper = new EventMapper(partyOwnerRepository,eventRepository);
        PartyOwner partyOwner = partyOwnerRepository.findById(ownerID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PartyOwner not found"));
        Event event =  eventMapper.createDTOToModel(createEventDTO,partyOwner);
        eventRepository.save(event);
        translateFun(event.getEventId());
    }

    /**
     * Atualiza as informações de um evento existente.
     *
     * @param eventId O ID do evento a ser atualizado.
     * @param updateEventDTO O DTO contendo os dados para atualização do evento.
     *
     * @throws ResponseStatusException Se o evento não for encontrado.
     */

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

    /**
     * Lista todos os eventos cadastrados.
     *
     * @return Uma lista com todos os eventos.
     */

    public List<Event> listAllEvent () {
        return eventRepository.findAll();
    }

    /**
     * Lista todos os eventos de um dono específico, retornando os dados no formato DTO.
     *
     * @param ownerID O ID do dono da festa.
     * @return Uma lista de eventos com os dados do dono da festa.
     */

    public List<EventWithPartyOwnerDTO> eventsByPartyOwner(UUID ownerID) {
        EventMapper eventMapper = new EventMapper(partyOwnerRepository,eventRepository);
        List<EventWithPartyOwnerDTO> allEvents = eventMapper.eventWithPartyOwnerDTO(ownerID);
        return allEvents;
    }

    /**
     * Exclui um evento baseado no seu ID.
     *
     * @param eventID O ID do evento a ser excluído.
     */

    public void deleteEvent (UUID eventID) {
        eventRepository.deleteById(eventID);
    }

    /**
     * Realiza a tradução assíncrona da descrição do evento, se ativado.
     *
     * @param eventId O ID do evento que deve ser traduzido.
     */

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
