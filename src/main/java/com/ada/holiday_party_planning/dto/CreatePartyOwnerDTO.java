package com.ada.holiday_party_planning.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreatePartyOwnerDTO {

    @NotBlank
    private String name;

    @NotBlank(message = "Email cannot be blank!")
    @Email
    private String email;

    @Size(min = 6, max = 8, message = "Password must be between 6 and 8 characters!")
    private String password;

    public CreatePartyOwnerDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
