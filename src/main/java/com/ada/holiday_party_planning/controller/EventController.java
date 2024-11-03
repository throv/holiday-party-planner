package com.ada.holiday_party_planning.controller;


import com.ada.holiday_party_planning.dto.ItemDTO;
import com.ada.holiday_party_planning.mappers.ItemMapper;
import com.ada.holiday_party_planning.service.EmailService;
import com.ada.holiday_party_planning.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {
    private final ItemService itemService;
    private final EmailService emailService;

    public EventController(ItemService itemService, EmailService emailService) {
        this.itemService = itemService;
        this.emailService = emailService;
    }

    @GetMapping("/{eventId}/items")
    public ResponseEntity<List<ItemDTO>> getEventItems(@PathVariable UUID eventId) {
        List<ItemDTO> items = itemService.getItems(eventId);

        if (items.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(items);
        }
    }
    @PostMapping("/{eventId}/items")
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO itemDTO,@PathVariable UUID eventId) {
        ItemDTO item = itemService.saveItem(ItemMapper.toModel(itemDTO, eventId));
        return ResponseEntity.ok(item);

    }

    @PostMapping("/{eventId}/send-email")
    public ResponseEntity<String> sendEventLinkEmail(@PathVariable UUID eventId, @RequestParam String email) {
        String eventLink = "http://localhost:8080/events/" + eventId;
        emailService.sendMail(email, "Event Link", eventLink);
        return ResponseEntity.ok("Email sent successfully");
    }
}
