package com.ada.holiday_party_planning.service;

import com.ada.holiday_party_planning.dto.CreateGuestDTO;
import com.ada.holiday_party_planning.dto.GuestDTO;
import com.ada.holiday_party_planning.mappers.GuestMapper;
import com.ada.holiday_party_planning.model.Guest;
import com.ada.holiday_party_planning.repository.GuestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GuestService {

    private GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<GuestDTO> getAllGuests() {
        List<Guest> allGuest = guestRepository.findAll();

        return GuestMapper.toDTOList(allGuest);
    }

    public Optional<GuestDTO> getGuestById(UUID id) {

        Optional<Guest> guest = guestRepository.findById(id);

        return guest.map(GuestMapper::toDTO);
    }

    public GuestDTO createGuest(CreateGuestDTO guest) {

        Optional<Guest> existingGuest = guestRepository.findByEmail(guest.getEmail());

        if(existingGuest.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already in use!");
        }

        Guest guestCreated = GuestMapper.createDTOToModel(guest);

        guestCreated.setConfirmed(guest.isConfirmed());

        guestRepository.save(guestCreated);

        return GuestMapper.toDTO(guestCreated);
    }

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

    public void deleteGuest(UUID guestId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Guest not found."));

        guestRepository.delete(guest);
    }
}
