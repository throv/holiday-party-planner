package com.ada.holiday_party_planning.service;

import com.ada.holiday_party_planning.exceptions.EventNotFoundException;
import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.model.Item;
import com.ada.holiday_party_planning.model.PartyOwner;
import com.ada.holiday_party_planning.repository.EventRepository;
import com.ada.holiday_party_planning.repository.ItemRepository;
import com.ada.holiday_party_planning.repository.PartyOwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final EventRepository eventRepository;


    public ItemService(ItemRepository itemRepository, EventRepository eventRepository) {
        this.itemRepository = itemRepository;
        this.eventRepository = eventRepository;
    }

    public Item createItem(Item item, UUID eventId){
        Event event = eventRepository.findById(eventId)
                .orElseThrow(EventNotFoundException::new);
        item.setEvent(event);
        return itemRepository.save(item);
    }

    public Item updateItem(Item item, UUID eventId){
        Event event = eventRepository.findById(eventId)
                .orElseThrow(EventNotFoundException::new);
        item.setEvent(event);
        return itemRepository.save(item);
    }

    public List<Item> itemsByEventId(UUID eventId) {
        List<Item> allItems = itemRepository.findAll();
        allItems.removeIf(item -> !eventId.equals(item.getEvent().getEventId()));
        return allItems;
    }

    public void deleteItem (UUID itemId) {
        itemRepository.deleteById(itemId);
    }

}
