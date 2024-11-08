package com.ada.holiday_party_planning.dto;

import com.ada.holiday_party_planning.enums.GuestStatusEnum;
import com.ada.holiday_party_planning.model.Event;

/**
 * DTO para a criação de um novo convidado para um evento.
 * Armazena os dados essenciais para o cadastro de um convidado,
 * incluindo nome, email, status, evento relacionado e confirmação de presença.
 */

public class CreateGuestDTO {

    private String name;
    private String email;
    private GuestStatusEnum status;
    private Event event;
    private boolean isConfirmed;

    /**
     * Construtor para inicializar o DTO com todos os atributos.
     *
     * @param name Nome do convidado.
     * @param email Email do convidado.
     * @param status Status do convidado (enum de status).
     * @param event Evento ao qual o convidado está associado.
     * @param isConfirmed Indica se a presença foi confirmada.
     */

    public CreateGuestDTO(String name, String email, GuestStatusEnum status,Event event, boolean isConfirmed) {
        this.name = name;
        this.email = email;
        this.status = status;
        this.event = event;
        this.isConfirmed = isConfirmed;
    }

    // Getters e Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GuestStatusEnum getStatus() {
        return status;
    }

    public void setStatus(GuestStatusEnum status) {
        this.status = status;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

}
