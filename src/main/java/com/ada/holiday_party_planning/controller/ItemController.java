package com.ada.holiday_party_planning.controller;

import com.ada.holiday_party_planning.dto.ItemDTO;
import com.ada.holiday_party_planning.mappers.ItemMapper;
import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.model.Item;
import com.ada.holiday_party_planning.service.EventService;
import com.ada.holiday_party_planning.service.ItemService;
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
    public List<ItemDTO> findByEvent(@PathVariable UUID eventId) {
        return ItemMapper.toDTOList(itemService.itemsByEventId(eventId));
    }

    @PostMapping("/{eventId}/create")
    public ItemDTO createItem(@PathVariable UUID eventId, @RequestBody ItemDTO item) {
        return ItemMapper.toDTO(
                itemService.createItem(
                        ItemMapper.toModel(item), eventId));
    }

    @PutMapping("{eventId}/update")
    public ItemDTO updateItem(@PathVariable UUID eventId, @RequestBody ItemDTO item) {
        return ItemMapper.toDTO(
                itemService.updateItem(
                        ItemMapper.toModel(item), eventId));
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable UUID id) {
        itemService.deleteItem(id);
    }

}
