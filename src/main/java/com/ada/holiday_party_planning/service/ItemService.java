package com.ada.holiday_party_planning.service;

import com.ada.holiday_party_planning.exceptions.EventNotFoundException;
import com.ada.holiday_party_planning.exceptions.ItemNotFoundException;
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

/**
 * Serviço para gerenciar itens e a lógica de negócios relacionada à criação, atualização, exclusão de itens
 * e sua associação com eventos específicos.
 *
 * Esta classe oferece operações para criar, atualizar, listar e excluir itens, além de associá-los a eventos.
 */

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final EventRepository eventRepository;

    /**
     * Construtor do serviço que recebe os repositórios necessários.
     *
     * @param itemRepository Repositório para manipulação de itens.
     * @param eventRepository Repositório para manipulação de eventos.
     */

    public ItemService(ItemRepository itemRepository, EventRepository eventRepository) {
        this.itemRepository = itemRepository;
        this.eventRepository = eventRepository;
    }

    /**
     * Cria um novo item e o associa ao evento especificado.
     *
     * @param item O item a ser criado.
     * @param eventId O ID do evento ao qual o item será associado.
     * @return O item criado.
     * @throws EventNotFoundException Se o evento não for encontrado.
     */

    public Item createItem(Item item, UUID eventId){
        Event event = eventRepository.findById(eventId)
                .orElseThrow(EventNotFoundException::new);
        item.setEvent(event);
        return itemRepository.save(item);
    }

    /**
     * Atualiza um item existente e o associa ao evento especificado.
     *
     * @param item O item a ser atualizado.
     * @param eventId O ID do evento ao qual o item será associado.
     * @return O item atualizado.
     * @throws EventNotFoundException Se o evento não for encontrado.
     */

    public Item updateItem(Item item, UUID eventId){
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

    public void deleteItem (UUID itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        itemRepository.delete(item);
    }

    //Serviço para verificar se um item está associado a um convidado específico
    public boolean isItemWithGuest(UUID itemId, UUID guestId) {
        return itemRepository.existsByIdAndGuestId(itemId, guestId);
    }


}
