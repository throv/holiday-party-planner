package com.ada.holiday_party_planning.mappers;

import com.ada.holiday_party_planning.dto.ItemDTO;
import com.ada.holiday_party_planning.model.Item;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável por realizar a conversão entre objetos DTO (Data Transfer Object) e entidades
 * relacionadas a itens de um evento, facilitando a transferência de dados entre as camadas de controle e serviço da aplicação.
 * Ela oferece métodos para transformar objetos de itens entre suas representações em DTOs e entidades.
 */

public class ItemMapper {

    /**
     * Converte um objeto Item (entidade) em um objeto ItemDTO (Data Transfer Object).
     *
     * @param item O objeto Item a ser convertido.
     * @return Um objeto ItemDTO representando o item.
     */

    public static ItemDTO toDTO(Item item) {
        return new ItemDTO(
                item.getItemId(),
                item.getName(),
                item.getQuantity(),
                item.getValue(),
                item.getGuest() != null ? GuestMapper.toDTO(item.getGuest()) : null);
    }

    /**
     * Converte um objeto ItemDTO (Data Transfer Object) em um objeto Item (entidade).
     *
     * @param itemDTO O objeto ItemDTO a ser convertido.
     * @return Um objeto Item representando a entidade do item.
     */

    public static Item toModel(ItemDTO itemDTO) {

        return new Item(
                itemDTO.getItemId(),
                itemDTO.getName(),
                itemDTO.getQuantity(),
                itemDTO.getValue(),
                null
        );
    }

    /**
     * Converte uma lista de objetos Item (entidades) em uma lista de objetos ItemDTO (Data Transfer Objects).
     *
     * @param itemList A lista de itens (Item) a ser convertida.
     * @return Uma lista de objetos ItemDTO representando os itens.
     */

    public static List<ItemDTO> toDTOList(List<Item> itemList) {
        return itemList.stream()
                .map(ItemMapper::toDTO)
                .collect(Collectors.toList());
    }
}
