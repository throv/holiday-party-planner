package com.ada.holiday_party_planning.controller;

import com.ada.holiday_party_planning.dto.ItemDTO;
import com.ada.holiday_party_planning.exceptions.ItemNotFoundException;
import com.ada.holiday_party_planning.mappers.ItemMapper;
import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.model.Guest;
import com.ada.holiday_party_planning.model.Item;
import com.ada.holiday_party_planning.repository.GuestRepository;
import com.ada.holiday_party_planning.repository.ItemRepository;
import com.ada.holiday_party_planning.service.EventService;
import com.ada.holiday_party_planning.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

/**
 * Controlador REST para gerenciar itens associados a eventos.
 * Define endpoints para criar, atualizar, listar e excluir itens de um evento.
 */

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository; //Repositório de itens

    @Autowired //Injeta automaticamente o GuestRepository, que é gerenciado pelo Spring Framework
    private GuestRepository guestRepository; //Injeta o repositório de Guest

    /**
     * Construtor que injeta o serviço de itens.
     *
     * @param itemService Serviço para manipulação de dados de itens.
     */
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Lista todos os itens associados a um evento específico.
     *
     * @param eventId ID do evento para o qual os itens serão listados.
     * @return Lista de itens associados ao evento especificado.
     */

    @GetMapping("/{eventId}/list")
    public ResponseEntity<List<ItemDTO>> findByEvent(@PathVariable UUID eventId) {
        return ResponseEntity.ok(ItemMapper.toDTOList(itemService.itemsByEventId(eventId)));
    }

    /**
     * Cria um novo item associado a um evento específico.
     *
     * @param eventId ID do evento ao qual o item será associado.
     * @param item Dados do item a ser criado.
     * @return Dados do item criado.
     */

    @PostMapping("/{eventId}/create")
    public ResponseEntity<ItemDTO> createItem(@PathVariable UUID eventId, @RequestBody ItemDTO item) {
        return ResponseEntity.ok(ItemMapper.toDTO(
                itemService.createItem(
                        ItemMapper.toModel(item), eventId)));
    }

    /**
     * Atualiza um item associado a um evento específico.
     *
     * @param eventId ID do evento ao qual o item pertence.
     * @param item Dados atualizados do item.
     * @return Dados do item atualizado.
     */

    @PutMapping("{eventId}/update")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable UUID eventId, @RequestBody ItemDTO item) {
        return ResponseEntity.ok(ItemMapper.toDTO(
                itemService.updateItem(
                        ItemMapper.toModel(item), eventId)));
    }

    /**
     * Exclui um item com base no ID fornecido.
     *
     * @param id ID do item a ser excluído.
     */

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

    //
    //Endpoint para listar todos os itens de um convidado específico
    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<Item>> getItemsByGuest(@PathVariable UUID guestId) {
        List<Item> items = itemRepository.findByGuestId(guestId);
        return ResponseEntity.ok(items);
    }

    //Endpoint para adicionar um novo item a um convidado específico
    @PostMapping("/guest/{guestId}")
    public ResponseEntity<Item> addItemToGuest(@PathVariable UUID guestId, @RequestBody Item item) {
        // Busca o Guest pelo ID no banco de dados
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Guest not found"));

        // Associa o convidado ao item
        item.setGuest(guest);

        // Salva o item no repositório
        Item createdItem = itemRepository.save(item);

        // Retorna o item criado com status HTTP 201 (Created)
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    //Endpoint para remover um item específico de um convidado
    @DeleteMapping("/guest/{guestId}/item/{itemId}")
    public ResponseEntity<Void> deleteItemFromGuest(@PathVariable UUID guestId, @PathVariable UUID itemId) {
        // Verifica se o item pertence ao convidado antes de deletar
        if (itemService.isItemWithGuest(itemId, guestId)) {
            itemRepository.deleteById(itemId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Item não encontrado para o convidado
        }
    }





}
