package com.ada.holiday_party_planning.controller;

import com.ada.holiday_party_planning.dto.PartyOwnerDTO;
import com.ada.holiday_party_planning.model.PartyOwner;
import com.ada.holiday_party_planning.service.PartyOwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ada.holiday_party_planning.repository.PartyOwnerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/party-owners")
public class PartyOwnerController {

    private final PartyOwnerService partyOwnerService;

    public PartyOwnerController(PartyOwnerService partyOwnerService) {
        this.partyOwnerService = partyOwnerService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPartyOwners() {
        List<PartyOwnerDTO> allPartyOwners = partyOwnerService.getAllPartyOwners();

        if(allPartyOwners.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .body("Message: No information found!");
        }

        return ResponseEntity.ok(allPartyOwners);
    }

    @PutMapping("/update/{ownerId}")
    public ResponseEntity<PartyOwnerDTO> updatePartyOwner(@RequestBody PartyOwnerDTO partyOwnerDTO, @PathVariable UUID ownerId) {

        Optional<PartyOwnerDTO> newPartyOwner = partyOwnerService.updatePartyOwner(ownerId, partyOwnerDTO);

        if(newPartyOwner.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(newPartyOwner.get());
        }
    }


/*    @PostMapping ("/register")
    public PartyOwner creatPartyOwner (@RequestBody PartyOwner partyOwner) {
        return  partyOwnerRepository.save(partyOwner);
    }*/

}
