package com.ada.holiday_party_planning.dto;

/**
 * DTO utilizado para representar a resposta após um login bem-sucedido de um proprietário de festa.
 * Contém informações do proprietário que são retornadas após a autenticação.
 */

public class PartyOwnerLoginResponseDTO {

    private String name;
    private String email;

    /**
     * Construtor padrão sem parâmetros
     */

    public PartyOwnerLoginResponseDTO() {}

    /**
     * Construtor que inicializa os dados do proprietário com nome e e-mail.
     *
     * @param name Nome do proprietário da festa.
     * @param email E-mail do proprietário da festa.
     */

    public PartyOwnerLoginResponseDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters e Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
