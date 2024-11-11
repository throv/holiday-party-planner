package com.ada.holiday_party_planning.repository;

import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface de repositório para a entidade Guest, estendendo a interface JpaRepository para fornecer
 * funcionalidades padrão de persistência de dados, como salvar, excluir, encontrar e listar convidados.
 *
 * Além disso, contém um método customizado para buscar um convidado por email.
 *
 * O Spring Data JPA automaticamente implementa essa interface para interagir com o banco de dados.
 */

public interface GuestRepository extends JpaRepository<Guest, UUID> {

    /**
     * Método customizado para encontrar um convidado pelo seu email.
     *
     * @param email O email do convidado a ser encontrado.
     * @return Um Optional contendo o convidado encontrado, se presente.
     */

    Optional<Guest> findByEmail(String email);

    List<Guest> findByEvent(Event event);
}
