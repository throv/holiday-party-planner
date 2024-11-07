package com.ada.holiday_party_planning.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO para representar os detalhes de um evento. Esta classe é usada para
 * transferir informações do evento, incluindo o tema, título, data, lugar,
 * descrição e informações relacionadas à categoria de diversão do evento.
 */

public class EventDTO {

    private final UUID eventID;
    private String theme;
    private String title;
    private LocalDateTime date;
    private String place;
    private String description;
    private String descriptionTranslateFun;
    private Boolean funActivate;
    private String categoryFun;
    private PartyOwnerDTO partyOwnerDTO;

    /**
     * Construtor para inicializar os dados principais de um evento.
     *
     * @param eventiD Identificador único do evento.
     * @param theme Tema do evento.
     * @param title Título do evento.
     * @param date Data e hora do evento.
     * @param place Local do evento.
     * @param description Descrição do evento.
     * @param descriptionTranslateFun Descrição traduzida relacionada à diversão.
     * @param funActivate Se a categoria de diversão está ativada.
     * @param categoryFun Categoria de diversão.
     */

    public EventDTO(UUID eventiD, String theme,
                                  String title, LocalDateTime date,
                                  String place, String description,
                                  String descriptionTranslateFun,
                                  Boolean funActivate, String categoryFun) {
        this.eventID = eventiD;
        this.theme = theme;
        this.title = title;
        this.date = date;
        this.place = place;
        this.description = description;
        this.descriptionTranslateFun = descriptionTranslateFun;
        this.funActivate = funActivate;
        this.categoryFun = categoryFun;
    }

    // Getters e Setters

    public UUID getEventID() {
        return eventID;
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
}
