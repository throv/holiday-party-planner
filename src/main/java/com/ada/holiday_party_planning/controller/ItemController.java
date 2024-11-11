package com.ada.holiday_party_planning.controller;

import com.ada.holiday_party_planning.dto.ItemDTO;
import com.ada.holiday_party_planning.mappers.ItemMapper;
import com.ada.holiday_party_planning.model.Guest;
import com.ada.holiday_party_planning.model.Item;
import com.ada.holiday_party_planning.repository.GuestRepository;
import com.ada.holiday_party_planning.repository.ItemRepository;
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
    public void deleteItem(@PathVariable UUID id) {
        itemService.deleteItem(id);
    }

    //
    //Endpoint para listar todos os itens de um convidado específico
    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<Item>> getItemsByGuest(@PathVariable UUID guestId) {
        return ResponseEntity.ok(itemService.itemsByGuestId(guestId));
    }

    //Endpoint para adicionar um novo item a um convidado específico
    @PostMapping("{itemId}/guest/{guestId}")
    public ResponseEntity<Item> addItemToGuest(@PathVariable UUID guestId, @PathVariable UUID itemId) {
        Item item = itemService.addItemToGuest(guestId, itemId);
        // Retorna o item criado com status HTTP 201 (Created)
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    //Endpoint para remover um item específico de um convidado
    @DeleteMapping("{itemId}/guest/{guestId}")
    public ResponseEntity<ItemDTO> deleteItemFromGuest(@PathVariable UUID guestId, @PathVariable UUID itemId) {
        Item item = itemService.removeGuestFromItem(guestId, itemId);
        return new ResponseEntity<>(ItemMapper.toDTO(item), HttpStatus.OK);
    }
}
