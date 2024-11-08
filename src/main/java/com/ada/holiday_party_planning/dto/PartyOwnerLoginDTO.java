package com.ada.holiday_party_planning.dto;

/**
 * DTO utilizado para capturar os dados de login de um proprietário de festa.
 * Contém o e-mail e a senha necessários para autenticar o usuário no sistema.
 */

public class PartyOwnerLoginDTO {

    private String email;
    private String password;

    /**
     * Construtor padrão sem parâmetros
     */

    public PartyOwnerLoginDTO() {}

    /**
     * Construtor que inicializa os dados de login.
     *
     * @param email E-mail do proprietário da festa.
     * @param password Senha do proprietário da festa.
     */

    public PartyOwnerLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters e Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
