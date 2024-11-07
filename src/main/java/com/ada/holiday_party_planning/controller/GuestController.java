package com.ada.holiday_party_planning.controller;

import com.ada.holiday_party_planning.dto.CreateGuestDTO;
import com.ada.holiday_party_planning.dto.GuestDTO;
import com.ada.holiday_party_planning.mappers.GuestMapper;
import com.ada.holiday_party_planning.model.Guest;
import com.ada.holiday_party_planning.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @GetMapping("/{guestId}")
    public ResponseEntity<GuestDTO> getGuestById(@PathVariable UUID guestId) {

        Optional<GuestDTO> guestDTO = guestService.getGuestById(guestId);

        if (guestDTO.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(guestDTO.get());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllGuests() {

        List<GuestDTO> allGuests = guestService.getAllGuests();

        if (allGuests.isEmpty()) {
            return ResponseEntity.status(404).body("Message: No information found!");
        }

        return ResponseEntity.ok(allGuests);
    }

    @PostMapping("/register")
    public ResponseEntity<GuestDTO> createGuest(@RequestBody CreateGuestDTO createGuestDTO) {

        Guest guest = guestService.createGuest(createGuestDTO);
        GuestDTO guestDTO = GuestMapper.toDTO(guest);
        return ResponseEntity.status(201).body(guestDTO);
    }

    @PutMapping("/update/{guestId}")
    public ResponseEntity<GuestDTO> updateGuest(@RequestBody GuestDTO guestDTO, @PathVariable UUID guestId) {

        Optional<GuestDTO> newGuest = guestService.updateGuest(guestId, guestDTO);

        if (newGuest.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(newGuest.get());
        }
    }

    @DeleteMapping("/delete/{guestId}")
    public ResponseEntity<String> deleteGuest(@PathVariable UUID guestId) {

        guestService.deleteGuest(guestId);
        return ResponseEntity.status(204).body("Message: Guest deleted successfully!");
    }
}
