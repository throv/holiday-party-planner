package com.ada.holiday_party_planning.service;

import com.ada.holiday_party_planning.exceptions.EventNotFoundException;
import com.ada.holiday_party_planning.exceptions.ItemNotFoundException;
import com.ada.holiday_party_planning.mappers.ItemMapper;
import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.model.Guest;
import com.ada.holiday_party_planning.model.Item;
import com.ada.holiday_party_planning.repository.EventRepository;
import com.ada.holiday_party_planning.repository.GuestRepository;
import com.ada.holiday_party_planning.repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

/**
 * Serviço para gerenciar itens e a lógica de negócios relacionada à criação, atualização, exclusão de itens
 * e sua associação com eventos específicos.
 * <p>
 * Esta classe oferece operações para criar, atualizar, listar e excluir itens, além de associá-los a eventos.
 */

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final EventRepository eventRepository;
    private final GuestRepository guestRepository;

    /**
     * Construtor do serviço que recebe os repositórios necessários.
     *
     * @param itemRepository  Repositório para manipulação de itens.
     * @param eventRepository Repositório para manipulação de eventos.
     * @param guestRepository Repositório para manipulação de convidados.
     */

    public ItemService(ItemRepository itemRepository, EventRepository eventRepository, GuestRepository guestRepository) {
        this.itemRepository = itemRepository;
        this.eventRepository = eventRepository;
        this.guestRepository = guestRepository;
    }

    /**
     * Cria um novo item e o associa ao evento especificado.
     *
     * @param item    O item a ser criado.
     * @param eventId O ID do evento ao qual o item será associado.
     * @return O item criado.
     * @throws EventNotFoundException Se o evento não for encontrado.
     */

    public Item createItem(Item item, UUID eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(EventNotFoundException::new);
        item.setEvent(event);
        return itemRepository.save(item);
    }

    /**
     * Atualiza um item existente e o associa ao evento especificado.
     *
     * @param item    O item a ser atualizado.
     * @param eventId O ID do evento ao qual o item será associado.
     * @return O item atualizado.
     * @throws EventNotFoundException Se o evento não for encontrado.
     */

    public Item updateItem(Item item, UUID eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(EventNotFoundException::new);
        item.setEvent(event);
        return itemRepository.save(item);
    }

    /**
     * Retorna todos os itens associados a um evento específico.
     *
     * @param eventId O ID do evento.
     * @return Uma lista de itens associados ao evento.
     */

    public List<Item> itemsByEventId(UUID eventId) {
        List<Item> allItems = itemRepository.findAll();
        allItems.removeIf(item -> !eventId.equals(item.getEvent().getEventId()));
        return allItems;
    }


    /**
     * Exclui um item baseado no seu ID.
     *
     * @param itemId O ID do item a ser excluído.
     */

    public void deleteItem(UUID itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        itemRepository.delete(item);
    }

    //
    //Serviço para verificar se um item está associado a um convidado específico
    public boolean isItemWithGuest(UUID itemId, UUID guestId) {
        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        return item.getGuest().getGuestId().equals(guestId);
    }


    public List<Item> itemsByGuestId(UUID guestId) {
        return itemRepository.findAll().stream().filter(item -> item.getGuest() != null && item.getGuest().getGuestId().equals(guestId)).toList();
    }

    public Item addItemToGuest(UUID guestId, UUID itemId) {
        // Busca o Guest pelo ID no banco de dados
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(GuestNotFoundException::new);

        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        // Associa o convidado ao item
        item.setGuest(guest);

        // Salva o item no repositório
        return itemRepository.save(item);

    }

    public Item removeGuestFromItem(UUID guestId, UUID itemId) {
        // Verifica se o item pertence ao convidado antes de deletar
        if (isItemWithGuest(itemId, guestId)) {
            Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
            // Remove relaçào do convidado com o item
            item.setGuest(null);

            // Salva o item no repositório
            return itemRepository.save(item);
        } else {
            throw new ItemNotFoundException(); // Item não encontrado para o convidado
        }

    }
}
