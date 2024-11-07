package com.ada.holiday_party_planning.dto;

import com.ada.holiday_party_planning.enums.GuestStatusEnum;

import java.util.UUID;

/**
 * DTO para representar as informações de um convidado em um evento.
 * Esta classe é usada para transferir os dados do convidado entre a camada de serviço e a camada de apresentação.
 */

public class GuestDTO {

    private UUID guestId;
    private String name;
    private String email;
    private GuestStatusEnum status;

    /**
     * Construtor para inicializar as informações de um convidado.
     *
     * @param guestId Identificador único do convidado.
     * @param name Nome do convidado.
     * @param email E-mail do convidado.
     * @param status Status do convidado no evento.
     */

    public GuestDTO(UUID guestId, String name, String email, GuestStatusEnum status) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    // Getters e Setters

    public UUID getGuestId() {
        return guestId;
    }

    public void setGuestId(UUID guestId) {
        this.guestId = guestId;
    }

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
}
