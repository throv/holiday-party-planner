package com.ada.holiday_party_planning.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para a criação de um novo organizador de festas.
 * Contém os dados básicos de cadastro, incluindo nome, email e senha,
 * com validações para garantir a integridade das informações.
 */

public class CreatePartyOwnerDTO {

    @NotBlank
    private String name;

    @NotBlank(message = "Email cannot be blank!")
    @Email
    private String email;

    @Size(min = 6, max = 8, message = "Password must be between 6 and 8 characters!")
    private String password;

    /**
     * Construtor para inicializar o DTO com todos os atributos.
     *
     * @param name Nome do organizador de festas.
     * @param email Email do organizador.
     * @param password Senha do organizador (deve ter entre 6 e 8 caracteres).
     */

    public CreatePartyOwnerDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
