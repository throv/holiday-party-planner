package com.ada.holiday_party_planning.dto;

import java.util.UUID;


/**
 * DTO que representa um proprietário de festa.
 * Contém os dados essenciais sobre o proprietário, como ID, nome e email.
 */

public class PartyOwnerDTO {

    private final UUID ownerId;
    private String name;
    private String email;

    /**
     * Construtor para inicializar os dados do proprietário de festa.
     *
     * @param ownerId Identificador único do proprietário.
     * @param name Nome do proprietário da festa.
     * @param email E-mail do proprietário da festa.
     */

    public PartyOwnerDTO(UUID ownerId, String name, String email) {
        this.ownerId = ownerId;
        this.name = name;
        this.email = email;
    }

    // Getters e Setters

    public UUID getOwnerId() {
        return ownerId;
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
}
