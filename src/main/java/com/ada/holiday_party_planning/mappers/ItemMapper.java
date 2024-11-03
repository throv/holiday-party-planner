package com.ada.holiday_party_planning.mappers;

import com.ada.holiday_party_planning.dto.ItemDTO;
import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.model.Item;

import java.util.List;
import java.util.UUID;


public class ItemMapper {
    public static ItemDTO toDTO(Item item) {
        return new ItemDTO(
                item.getItemId(),
                item.getName(),
                item.getQuantity(),
                item.getValue(),
                item.getEventId());
    }

    public static Item toModel(ItemDTO itemDTO, UUID eventId) {
        return new Item(
                itemDTO.getItemId(),
                itemDTO.getName(),
                itemDTO.getQuantity(),
                itemDTO.getValue(),
                eventId
        );
    }

    public static List<ItemDTO> toDTOList(List<Item> items) {
        return items.stream()
                .map(ItemMapper::toDTO)
                .toList();
    }

}
