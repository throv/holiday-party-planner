package com.ada.holiday_party_planning.controller;

import com.ada.holiday_party_planning.dto.CreatePartyOwnerDTO;
import com.ada.holiday_party_planning.dto.PartyOwnerDTO;
import com.ada.holiday_party_planning.dto.PartyOwnerLoginDTO;
import com.ada.holiday_party_planning.service.PartyOwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping ("/register")
    public ResponseEntity<PartyOwnerDTO> createPartyOwner (@RequestBody CreatePartyOwnerDTO createPartyOwnerDTO) {

        PartyOwnerDTO partyOwnerDTO = partyOwnerService.createPartyOwner(createPartyOwnerDTO);

        return ResponseEntity
                .status(201)
                .body(partyOwnerDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<PartyOwnerDTO> login(@RequestBody PartyOwnerLoginDTO partyOwnerLoginDTO) {
        PartyOwnerDTO partyOwnerDTO = partyOwnerService.login(partyOwnerLoginDTO);
        return ResponseEntity
                .status(200)
                .body(partyOwnerDTO);
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

}
