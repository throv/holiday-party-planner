package com.ada.holiday_party_planning.model;

import jakarta.persistence.*;
import java.util.UUID;

import org.springframework.http.HttpStatus;


/**
 * Representa um item necessário para um evento, contendo informações sobre o nome, quantidade, valor e o evento
 * ao qual o item está associado. Essa classe é utilizada para armazenar os itens necessários para o planejamento
 * de um evento, como alimentos, bebidas, materiais decorativos, entre outros.
 */

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "item_Id", nullable = false)
    private UUID itemId;

    @Column(name ="name", nullable = false)
    private String name;

    @Column(name="quantity", nullable = false)
    private int quantity;

    @Column(name= "value", nullable = false)
    private double value;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    //Relacionamento Many-to-One entre Item e Guest
    //Um convidado (Guest) pode ter vários itens (Item) associados, mas cada item pertence a um único convidado
    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;


    /**
     * Construtor padrão.
     */

    public Item() {
    }

    /**
     * Construtor para criar um item com informações específicas.
     *
     * @param itemId ID único do item.
     * @param name Nome do item.
     * @param quantity Quantidade necessária do item.
     * @param value Valor unitário do item.
     * @param event Evento ao qual o item pertence.
     */

    public Item(UUID itemId, String name, int quantity, double value, Event event) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.value = value;
        this.event = event;
    }

    //Getters e Setters

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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }


}

