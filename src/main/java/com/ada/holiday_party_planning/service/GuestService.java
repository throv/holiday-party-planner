package com.ada.holiday_party_planning.service;

import com.ada.holiday_party_planning.dto.CreateGuestDTO;
import com.ada.holiday_party_planning.dto.GuestDTO;
import com.ada.holiday_party_planning.mappers.GuestMapper;
import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.model.Guest;
import com.ada.holiday_party_planning.repository.EventRepository;
import com.ada.holiday_party_planning.repository.GuestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Serviço para gerenciar convidados e a lógica de negócios relacionada aos convidados e seus eventos.
 *
 * Esta classe oferece operações para criar, atualizar, listar, excluir convidados e associá-los aos eventos.
 */

@Service
public class GuestService {


    private final GuestRepository guestRepository;
    private final EventRepository eventRepository;

    /**
     * Construtor do serviço que recebe os repositórios necessários.
     *
     * @param guestRepository Repositório para manipulação de convidados.
     * @param eventRepository Repositório para manipulação de eventos.
     */

    public GuestService(GuestRepository guestRepository, EventRepository eventRepository) {
        this.guestRepository = guestRepository;
        this.eventRepository = eventRepository;
    }

    /**
     * Retorna todos os convidados cadastrados.
     *
     * @return Uma lista com todos os convidados no formato DTO.
     */

    public List<GuestDTO> getAllGuests() {
        List<Guest> allGuest = guestRepository.findAll();

        return GuestMapper.toDTOList(allGuest);
    }

    /**
     * Retorna um convidado específico baseado no seu ID.
     *
     * @param id O ID do convidado.
     * @return O DTO do convidado, caso encontrado.
     */

    public Optional<GuestDTO> getGuestById(UUID id) {

        Optional<Guest> guest = guestRepository.findById(id);

        return guest.map(GuestMapper::toDTO);
    }

    /**
     * Cria um novo convidado e o associa a um evento, se necessário.
     *
     * @param guest O DTO contendo os dados do novo convidado.
     * @return O convidado criado.
     * @throws ResponseStatusException Se o e-mail do convidado já estiver em uso.
     */

    @Transactional
    public Guest createGuest(CreateGuestDTO guest) {

        Optional<Guest> existingGuest = guestRepository.findByEmail(guest.getEmail());

        if(existingGuest.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already in use!");
        }

        Event event = guest.getEvent();

        if (event != null && event.getEventId() == null) {
            event = eventRepository.save(event);
            guest.setEvent(event);
        }

        Guest guestCreated = GuestMapper.createDTOToModel(guest);

        guestCreated.setConfirmed(guest.isConfirmed());

        return guestRepository.save(guestCreated);
    }

    /**
     * Atualiza as informações de um convidado existente.
     *
     * @param guestId O ID do convidado a ser atualizado.
     * @param newGuestDTO O DTO contendo os novos dados do convidado.
     * @return O DTO do convidado atualizado, caso encontrado.
     */

    public Optional<GuestDTO> updateGuest(UUID guestId, GuestDTO newGuestDTO) {

        Optional<Guest> oldGuest = guestRepository.findById(guestId);

        if(oldGuest.isPresent()) {
            Guest existingGuest = oldGuest.get();

            GuestMapper.updateGuestDTO(newGuestDTO, existingGuest);

            guestRepository.save(existingGuest);

            return Optional.of(GuestMapper.toDTO(existingGuest));
        }
        return Optional.empty();
    }

    /**
     * Exclui um convidado baseado no seu ID.
     *
     * @param guestId O ID do convidado a ser excluído.
     * @throws ResponseStatusException Se o convidado não for encontrado.
     */

    public void deleteGuest(UUID guestId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Guest not found."));

        guestRepository.delete(guest);
    }
}
