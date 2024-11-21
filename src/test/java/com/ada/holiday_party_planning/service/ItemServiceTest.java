package com.ada.holiday_party_planning.service;

import com.ada.holiday_party_planning.exceptions.EventNotFoundException;
import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.model.Item;
import com.ada.holiday_party_planning.repository.EventRepository;
import com.ada.holiday_party_planning.repository.GuestRepository;
import com.ada.holiday_party_planning.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.mockito.Mockito;
import org.mockito.exceptions.verification.NoInteractionsWanted;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    private ItemService itemService;
    private EventRepository eventRepository;
    private ItemRepository itemRepository;
    private GuestRepository guestRepository;

    @BeforeEach
    void setUp() {
        eventRepository = mock(EventRepository.class);
        itemRepository = mock(ItemRepository.class);
        guestRepository = mock(GuestRepository.class);
        itemService = new ItemService(itemRepository, eventRepository, guestRepository);


    }

    @Test
    void dadoUmItemEUmEventIdValidos_quandoCreateItem_entaoRetornarItemSalvo() {
        // Dado
        UUID eventId = UUID.randomUUID();
        Item item = new Item();
        item.setName("chester");
        item.setQuantity(1);
        item.setValue(60.0);
        Event event = new Event();
        event.setEventId(eventId);
        event.setDate(LocalDateTime.now());
        event.setPlace("Casa da Monica");
        event.setTheme("Ceia de Natal");
        event.setDescription("Rua do Limoeiro");

        when(eventRepository.findById(eventId))
                .thenReturn(Optional.of(event));

        when(itemRepository.save(item))
                .thenAnswer(invocation -> {
                    Item savedItem = invocation.getArgument(0);
                    savedItem.setItemId(UUID.randomUUID()); // simula geracao Id
                    return savedItem;
                });

        // Quando
        Item savedItem = itemService.createItem(item, eventId);

        // Entao
        assertNotNull(savedItem);
        assertEquals(savedItem.getEvent(), event);
        verify(eventRepository, times(1)).findById(eventId);
        verify(itemRepository, times(1)).save(item);


    }

    @Test
    void dadoUmItemEUmEventoInexistente_quandoCreateItem_entaoLancaEventNotFoundException() {
        // Dado
        UUID eventId = UUID.randomUUID();
        Item item = new Item();
        item.setName("chester");
        item.setQuantity(1);
        item.setValue(60.0);

        when(eventRepository.findById(eventId))
                .thenReturn(Optional.empty());

        // Quando e Entao
        assertThrows(EventNotFoundException.class, () -> itemService.createItem(item, eventId));
        verifyNoMoreInteractions(itemRepository);


    }


    @Test
    void updateItem() {
    }

    @Test
    void itemsByEventId() {
    }

    @Test
    void deleteItem() {
    }

    @Test
    void isItemWithGuest() {
    }

    @Test
    void itemsByGuestId() {
    }

    @Test
    void addItemToGuest() {
    }

    @Test
    void removeGuestFromItem() {
    }
}