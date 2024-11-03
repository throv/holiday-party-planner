package com.ada.holiday_party_planning.service;

import com.ada.holiday_party_planning.dto.CreatePartyOwnerDTO;
import com.ada.holiday_party_planning.dto.PartyOwnerDTO;
import com.ada.holiday_party_planning.dto.PartyOwnerLoginDTO;
import com.ada.holiday_party_planning.mappers.PartyOwnerMapper;
import com.ada.holiday_party_planning.model.PartyOwner;
import com.ada.holiday_party_planning.repository.PartyOwnerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PartyOwnerService {

    private final PartyOwnerRepository partyOwnerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public PartyOwnerService(PartyOwnerRepository partyOwnerRepository) {
        this.partyOwnerRepository = partyOwnerRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public PartyOwnerDTO createPartyOwner(CreatePartyOwnerDTO createPartyOwnerDTO) {

        Optional<PartyOwner> existingPartyOwner = partyOwnerRepository.findByEmail(createPartyOwnerDTO.getEmail());

        if(existingPartyOwner.isPresent()) {
            throw new IllegalArgumentException("Email is already in use!");
        }

        PartyOwner partyOwnerCreated = PartyOwnerMapper.createDTOToModel(createPartyOwnerDTO);

        partyOwnerRepository.save(partyOwnerCreated);

        return PartyOwnerMapper.toDTO(partyOwnerCreated);

    }

    public PartyOwnerDTO login(PartyOwnerLoginDTO userLoginInfo) {
        Optional<PartyOwner> existingPartyOwner = partyOwnerRepository.findByEmail(userLoginInfo.getEmail());

        if(existingPartyOwner.isEmpty()) {
            throw new IllegalArgumentException("Email or password is not valid.");
        }

        PartyOwner partyOwner = existingPartyOwner.get();

        if(!passwordEncoder.matches(userLoginInfo.getPassword(), partyOwner.getPassword())) {
            throw new IllegalArgumentException("Email or password is not valid.");
        }

        return PartyOwnerMapper.toDTO(partyOwner);
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
