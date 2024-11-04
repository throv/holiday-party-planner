package com.ada.holiday_party_planning.mappers;

import com.ada.holiday_party_planning.dto.ItemDTO;
import com.ada.holiday_party_planning.model.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ItemMapper {

    public static ItemDTO toDTO(Item item) {
        return new ItemDTO(
                item.getItemId(),
                item.getName(),
                item.getQuantity(),
                item.getValue());
    }

    public static Item toModel(ItemDTO itemDTO) {

        return new Item(
                itemDTO.getItemId(),
                itemDTO.getName(),
                itemDTO.getQuantity(),
                itemDTO.getValue(),
                null
        );
    }

    public static List<ItemDTO> toDTOList(List<Item> itemList) {
        return itemList.stream()
                .map(ItemMapper::toDTO)
                .collect(Collectors.toList());
    }
}
