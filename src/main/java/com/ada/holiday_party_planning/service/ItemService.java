package com.ada.holiday_party_planning.service;

import com.ada.holiday_party_planning.model.Item;

public interface ItemService {
    Item createItem(Item item);
    Item updateItem(String id, Item item);
    void deleteItem(String id);
}
