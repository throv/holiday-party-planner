package com.ada.holiday_party_planning.model;

import com.ada.holiday_party_planning.enums.GuestStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Representa um evento planejado, contendo informações sobre o tema, título, data, local,
 * descrição, categoria de diversão e o dono da festa. Também contém listas de convidados e itens
 * associados ao evento. A classe oferece métodos para adicionar, remover convidados e itens,
 * além de calcular o custo total do evento e filtrar os convidados confirmados ou não confirmados.
 */

@Entity
@Table(name = "events_planned")
public class Event {

    /**
     * ID único do evento.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "event_id")
    private UUID eventId;

    /**
     * Tema do evento.
     */

    private String theme;

    /**
     * Título do evento.
     */

    private String title;

    /**
     * Data e hora do evento.
     */

    private LocalDateTime date;

    /**
     * Local do evento.
     */

    private String place;

    /**
     * Descrição do evento.
     */

    private String description;

    /**
     * Descrição traduzida do evento, usada para diversão.
     */

    @Column(name = "description_translate_fun")
    private String descriptionTranslateFun = "TESTE";

    /**
     * Indica se a diversão do evento está ativada.
     */

    private Boolean funActivate;

    /**
     * Categoria da diversão do evento.
     */

    private String categoryFun;

    /**
     * Dono do evento.
     */

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "owner_id", nullable = false)
    @JsonIgnore
    private PartyOwner partyOwner;

    /**
     * Lista de convidados do evento.
     */

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Guest> guests;

    /**
     * Lista de itens do evento.
     */

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Item> items;

    /**
     * Construtor padrão.
     */

    public Event() {}

    /**
     * Construtor para criar um evento com informações específicas.
     *
     * @param theme Tema do evento.
     * @param title Título do evento.
     * @param date Data e hora do evento.
     * @param place Local do evento.
     * @param description Descrição do evento.
     * @param funActivate Indica se a diversão do evento está ativada.
     * @param categoryFun Categoria da diversão do evento.
     * @param partyOwner Dono do evento.
     */

    public Event (String theme, String title, LocalDateTime date,
                  String place, String description,
                  Boolean funActivate, String categoryFun, PartyOwner partyOwner) {
        this.theme = theme;
        this.title = title;
        this.date = date;
        this.place = place;
        this.description = description;
        this.funActivate = funActivate;
        this.categoryFun = categoryFun;
        this.partyOwner = partyOwner;

    }

    // Getters e Setters

    public UUID getEventId() {
        return eventId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public PartyOwner getOwner() {
        return partyOwner;
    }

    public void setOwner(PartyOwner partyOwner) {
        this.partyOwner = partyOwner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionTranslateFun() {
        return descriptionTranslateFun;
    }

    public void setDescriptionTranslateFun(String descriptionTranslateFun) {
        this.descriptionTranslateFun = descriptionTranslateFun;
    }

    public Boolean getFunActivate() {
        return funActivate;
    }

    public void setFunActivate(Boolean funActivate) {
        this.funActivate = funActivate;
    }

    public String getCategoryFun() {
        return categoryFun;
    }

    public void setCategoryFun(String categoryFun) {
        this.categoryFun = categoryFun;
    }

    /**
     * Adiciona um convidado à lista de convidados do evento.
     *
     * @param guest O convidado a ser adicionado.
     */

    public void addGuest(Guest guest) {
        guests.add(guest);
        guest.setEvent(this);
    }

    /**
     * Adiciona um item à lista de itens do evento.
     *
     * @param item O item a ser adicionado.
     */

    public void addItem(Item item) {
        items.add(item);
        item.setEvent(this);
    }

    /**
     * Remove um convidado da lista de convidados do evento.
     *
     * @param guest O convidado a ser removido.
     */

    public void deleteGuest(Guest guest) {
        guests.remove(guest);
        guest.setEvent(null);
    }

    /**
     * Remove um item da lista de itens do evento.
     *
     * @param item O item a ser removido.
     */

    public void deleteItem(Item item) {
        items.remove(item);
        item.setEvent(null);
    }

    /**
     * Calcula o custo total do evento com base na quantidade e valor dos itens.
     *
     * @return O custo total do evento.
     */

    public double totalEventCost() {
        return items
                .stream()
                .mapToDouble(item -> item.getQuantity() * item.getValue()).sum();
    }

    /**
     * Filtra os convidados confirmados do evento.
     *
     * @return Lista de convidados confirmados.
     */

    public List<Guest> confirmedGuests() {
        return guests
                .stream()
                .filter(guest -> guest.getStatus() == GuestStatusEnum.CONFIRMED)
                .toList();
    }

    /**
     * Filtra os convidados não confirmados do evento.
     *
     * @return Lista de convidados não confirmados.
     */

    public List<Guest> notConfirmedGuests() {
        return guests
                .stream()
                .filter(guest -> guest.getStatus() == GuestStatusEnum.DECLINED)
                .toList();
    }
}
