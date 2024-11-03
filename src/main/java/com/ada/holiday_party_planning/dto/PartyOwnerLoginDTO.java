package com.ada.holiday_party_planning.dto;

public class PartyOwnerLoginDTO {

    private String email;
    private String password;

    public PartyOwnerLoginDTO() {}

    public PartyOwnerLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
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
