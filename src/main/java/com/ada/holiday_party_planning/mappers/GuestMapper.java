package com.ada.holiday_party_planning.mappers;

import com.ada.holiday_party_planning.dto.CreateGuestDTO;
import com.ada.holiday_party_planning.dto.GuestDTO;
import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.model.Guest;

import java.util.List;
import java.util.stream.Collectors;

public class GuestMapper {

    public static GuestDTO toDTO(Guest guest) {

        return new GuestDTO(
            guest.getGuestId(),
            guest.getName(),
            guest.getEmail(),
            guest.getStatus()
        );
    }

    public static Guest toEntity(GuestDTO guestDTO, Event event) {

        Guest guest = new Guest();
        guest.setGuestId(guestDTO.getGuestId());
        guest.setName(guestDTO.getName());
        guest.setEmail(guestDTO.getEmail());
        guest.setStatus(guestDTO.getStatus());
        guest.setEvent(event);
        return guest;
    }

    public static List<GuestDTO> toDTOList(List<Guest> guestsList) {
        return guestsList.stream().map(GuestMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static Guest createDTOToModel(CreateGuestDTO createGuestDTO) {
        Guest guest = new Guest();
        guest.setName(createGuestDTO.getName());
        guest.setEmail(createGuestDTO.getEmail());
        guest.setStatus(createGuestDTO.getStatus());
        guest.setConfirmed(createGuestDTO.isConfirmed());
        guest.setEvent(createGuestDTO.getEvent());
        return guest;
    }

    public static void updateGuestDTO(GuestDTO guestDTO, Guest guest) {
        guest.setName(guestDTO.getName());
        guest.setEmail(guestDTO.getEmail());
        guest.setStatus(guestDTO.getStatus());
    }
}
