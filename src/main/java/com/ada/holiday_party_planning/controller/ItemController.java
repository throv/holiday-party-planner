package com.ada.holiday_party_planning.controller;

import com.ada.holiday_party_planning.dto.ItemDTO;
import com.ada.holiday_party_planning.mappers.ItemMapper;
import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.model.Item;
import com.ada.holiday_party_planning.service.EventService;
import com.ada.holiday_party_planning.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controlador REST para gerenciar itens associados a eventos.
 * Define endpoints para criar, atualizar, listar e excluir itens de um evento.
 */

@RestController
@RequestMapping("/item")
public class ItemController {

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
    public List<ItemDTO> findByEvent(@PathVariable UUID eventId) {
        return ItemMapper.toDTOList(itemService.itemsByEventId(eventId));
    }

    /**
     * Cria um novo item associado a um evento específico.
     *
     * @param eventId ID do evento ao qual o item será associado.
     * @param item Dados do item a ser criado.
     * @return Dados do item criado.
     */

    @PostMapping("/{eventId}/create")
    public ItemDTO createItem(@PathVariable UUID eventId, @RequestBody ItemDTO item) {
        return ItemMapper.toDTO(
                itemService.createItem(
                        ItemMapper.toModel(item), eventId));
    }

    /**
     * Atualiza um item associado a um evento específico.
     *
     * @param eventId ID do evento ao qual o item pertence.
     * @param item Dados atualizados do item.
     * @return Dados do item atualizado.
     */

    @PutMapping("{eventId}/update")
    public ItemDTO updateItem(@PathVariable UUID eventId, @RequestBody ItemDTO item) {
        return ItemMapper.toDTO(
                itemService.updateItem(
                        ItemMapper.toModel(item), eventId));
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

}
