package com.ada.holiday_party_planning.dto;

import java.time.LocalDateTime;

/**
 * DTO para criação de eventos.
 * Armazena as informações necessárias para criar um novo evento, incluindo tema, título, data, local, descrição,
 * opções de entretenimento e informações do proprietário da festa.
 */

public class CreateEventDTO {
        private String theme;
        private String title;
        private LocalDateTime date;
        private String place;
        private String description;
        private Boolean funActivate;
        private String categoryFun;
        private PartyOwnerDTO partyOwnerDTO;

    /**
     * Construtor da classe CreateEventDTO.
     *
     * @param theme Tema do evento.
     * @param title Título do evento.
     * @param date Data e hora do evento.
     * @param place Local onde o evento ocorrerá.
     * @param description Descrição do evento.
     * @param funActivate Indica se haverá entretenimento no evento.
     * @param categoryFun Categoria do entretenimento, se ativado.
     * @param partyOwnerDTO Dados do proprietário do evento.
     */

    public CreateEventDTO(String theme, String title, LocalDateTime date,
                          String place, String description,
                          Boolean funActivate, String categoryFun,
                          PartyOwnerDTO partyOwnerDTO) {
        this.theme = theme;
        this.title = title;
        this.date = date;
        this.place = place;
        this.description = description;
        this.funActivate = funActivate;
        this.categoryFun = categoryFun;
        this.partyOwnerDTO = partyOwnerDTO;
    }

    // Getters e setters

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

    public PartyOwnerDTO getPartyOwnerDTO() {
        return partyOwnerDTO;
    }

    public void setPartyOwnerDTO(PartyOwnerDTO partyOwnerDTO) {
        this.partyOwnerDTO = partyOwnerDTO;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

