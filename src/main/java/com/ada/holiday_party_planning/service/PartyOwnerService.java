package com.ada.holiday_party_planning.service;

import com.ada.holiday_party_planning.dto.PartyOwnerDTO;
import com.ada.holiday_party_planning.mappers.PartyOwnerMapper;
import com.ada.holiday_party_planning.model.PartyOwner;
import com.ada.holiday_party_planning.repository.PartyOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PartyOwnerService {

    private final PartyOwnerRepository partyOwnerRepository;

    public PartyOwnerService(PartyOwnerRepository partyOwnerRepository) {
        this.partyOwnerRepository = partyOwnerRepository;
    }

    public List<PartyOwnerDTO> getAllPartyOwners() {
        List<PartyOwner> partyOwnersList = partyOwnerRepository.findAll();

        return PartyOwnerMapper.toDTOList(partyOwnersList);
    }

    public Optional<PartyOwnerDTO> updatePartyOwner(UUID ownerId, PartyOwnerDTO newPartyOwner) {

        Optional<PartyOwner> oldPartyOwner = partyOwnerRepository.findById(ownerId);

        if(oldPartyOwner.isPresent()) {
            PartyOwner existingPartyOwner = oldPartyOwner.get();
            PartyOwnerMapper.updatePartyOwnerDTO(newPartyOwner, existingPartyOwner);

            partyOwnerRepository.save(existingPartyOwner);

            return Optional.of(PartyOwnerMapper.toDTO(existingPartyOwner));
        }

        return Optional.empty();
    }
}
