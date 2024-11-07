package com.ada.holiday_party_planning.controller;

import com.ada.holiday_party_planning.dto.ItemDTO;
import com.ada.holiday_party_planning.exceptions.ItemNotFoundException;
import com.ada.holiday_party_planning.mappers.ItemMapper;
import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.model.Item;
import com.ada.holiday_party_planning.service.EventService;
import com.ada.holiday_party_planning.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{eventId}/list")
    public ResponseEntity<List<ItemDTO>> findByEvent(@PathVariable UUID eventId) {
        return ResponseEntity.ok(ItemMapper.toDTOList(itemService.itemsByEventId(eventId)));
    }

    @PostMapping("/{eventId}/create")
    public ResponseEntity<ItemDTO> createItem(@PathVariable UUID eventId, @RequestBody ItemDTO item) {
        return ResponseEntity.ok(ItemMapper.toDTO(
                itemService.createItem(
                        ItemMapper.toModel(item), eventId)));
    }

    @PutMapping("{eventId}/update")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable UUID eventId, @RequestBody ItemDTO item) {
        return ResponseEntity.ok(ItemMapper.toDTO(
                itemService.updateItem(
                        ItemMapper.toModel(item), eventId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
        try{
            itemService.deleteItem(id);
            return ResponseEntity.noContent().build();
        }
        catch(ItemNotFoundException e ){
            return ResponseEntity.notFound().build();
        }

    }

}
