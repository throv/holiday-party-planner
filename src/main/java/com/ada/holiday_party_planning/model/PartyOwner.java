package com.ada.holiday_party_planning.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa o proprietário de uma festa (Party Owner), contendo informações como nome, e-mail, senha e os eventos
 * associados a este proprietário. Essa classe gerencia os dados do organizador de festas e os eventos que ele criou.
 */

@Entity
@Table(name = "party_owner")
public class PartyOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "owner_id", nullable = false)
    private UUID ownerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "partyOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    /**
     * Construtor padrão.
     */

    public PartyOwner() {}

    /**
     * Construtor para criar um novo proprietário de festa com dados específicos.
     *
     * @param name Nome do proprietário da festa.
     * @param email E-mail do proprietário da festa.
     * @param password Senha do proprietário da festa.
     */

    public PartyOwner(String name, String email, String password) {
        this.name = name;
        this.email = email;
        setPassword(password);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public List<Event> getAllEvents() {
        return this.events;
    }*/

    /**
     * Adiciona um evento à lista de eventos do proprietário da festa e define o proprietário para o evento.
     *
     * @param event O evento a ser adicionado.
     */

    public void addEvent(Event event) {
        events.add(event);
        event.setOwner(this);
    }

    /**
     * Remove um evento da lista de eventos do proprietário da festa e limpa o relacionamento do evento.
     *
     * @param event O evento a ser removido.
     */

    public void removeEvent(Event event) {
        events.remove(event);
        event.setOwner(null);
    }

    /**
     * Compara se dois objetos PartyOwner são iguais com base no ID único.
     *
     * @param o O objeto a ser comparado.
     * @return true se os objetos são iguais, false caso contrário.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartyOwner that = (PartyOwner) o;
        return Objects.equals(ownerId, that.ownerId);
    }

    /**
     * Gera o código hash baseado no ID do proprietário.
     *
     * @return O código hash para o proprietário da festa.
     */

    @Override
    public int hashCode() {
        return Objects.hash(ownerId);
    }
}
