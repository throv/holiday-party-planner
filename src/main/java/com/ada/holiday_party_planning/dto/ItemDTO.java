package com.ada.holiday_party_planning.dto;

import java.util.UUID;

/**
 * DTO para representar os detalhes de um item (provavelmente um item para uma festa ou evento).
 * A classe é usada para transferir os dados do item entre as camadas do sistema.
 */

public class ItemDTO {
    private UUID itemId;
    private String name;
    private int quantity;
    private double value;

    /**
     * Construtor para inicializar os atributos do item.
     *
     * @param itemId Identificador único do item.
     * @param name Nome do item.
     * @param quantity Quantidade disponível do item.
     * @param value Valor unitário do item.
     */

    public ItemDTO(UUID itemId, String name, int quantity, double value) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.value = value;
    }

    // Getters e Setters

    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
