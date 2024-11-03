package com.ada.holiday_party_planning.service;

import com.ada.holiday_party_planning.dto.ItemDTO;
import com.ada.holiday_party_planning.mappers.ItemMapper;
import com.ada.holiday_party_planning.model.Item;
import com.ada.holiday_party_planning.repository.ItemRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
public class ItemService {
    private final ItemRepository itemRepository;


    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemDTO saveItem(Item item) {
        return ItemMapper.toDTO(itemRepository.save(item));
    }

    public List<ItemDTO> getItems(UUID eventId) {
        Item example = new Item();
        example.setEventId(eventId);
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnorePaths("itemId","name","quantity","value")
                .withMatcher("eventId", exact());
        List<Item> eventItems = itemRepository.findAll(Example.of(example, matcher));
        return ItemMapper.toDTOList(eventItems);
    }

    public ItemDTO getById(UUID itemId){
        Optional<Item> item =  itemRepository.findById(itemId);
        return item.map(ItemMapper::toDTO).orElse(null);
    }

    public void deleteItem(Item item){
        itemRepository.delete(item);
    }

}
