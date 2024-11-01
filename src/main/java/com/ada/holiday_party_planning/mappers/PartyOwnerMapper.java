package com.ada.holiday_party_planning.mappers;

import com.ada.holiday_party_planning.dto.PartyOwnerDTO;
import com.ada.holiday_party_planning.model.PartyOwner;

import java.util.List;
import java.util.stream.Collectors;

public class PartyOwnerMapper {

    public static PartyOwnerDTO toDTO(PartyOwner partyOwner) {
        return new PartyOwnerDTO(
                partyOwner.getOwnerId(),
                partyOwner.getName(),
                partyOwner.getEmail()
        );
    }

    public static PartyOwner toModel(PartyOwnerDTO partyOwnerDTO) {

        return new PartyOwner(
                partyOwnerDTO.getName(),
                partyOwnerDTO.getEmail(),
                null
        );
    }

    public static List<PartyOwnerDTO> toDTOList(List<PartyOwner> partyOwnerList) {
        return partyOwnerList.stream()
                .map(PartyOwnerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static void updatePartyOwnerDTO(PartyOwnerDTO ownerDTO, PartyOwner partyOwner) {
        partyOwner.setName(ownerDTO.getName());
        partyOwner.setEmail(ownerDTO.getEmail());
    }
}
