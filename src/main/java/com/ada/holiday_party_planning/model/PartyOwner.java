package com.ada.holiday_party_planning.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "party_owner")
@Table(name = "party_owner")
public class PartyOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "owner_Id", nullable = false)
    private UUID ownerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public PartyOwner() {

    }

    public PartyOwner(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartyOwner that = (PartyOwner) o;
        return Objects.equals(ownerId, that.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId);
    }
}
