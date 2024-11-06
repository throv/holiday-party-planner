package com.ada.holiday_party_planning.dto;

public class PartyOwnerLoginResponseDTO {

    private String name;
    private String email;

    public PartyOwnerLoginResponseDTO() {}

    public PartyOwnerLoginResponseDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

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
