package com.ada.holiday_party_planning.repository;

import com.ada.holiday_party_planning.dto.PartyOwnerDTO;
import com.ada.holiday_party_planning.model.PartyOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface de repositório para a entidade PartyOwner, estendendo a interface JpaRepository para fornecer
 * funcionalidades padrão de persistência de dados, como salvar, excluir, encontrar e listar os donos de festas.
 *
 * O Spring Data JPA automaticamente implementa essa interface para interagir com o banco de dados.
 */

public interface PartyOwnerRepository extends JpaRepository<PartyOwner,UUID>{

    /**
     * Busca um dono de festa (PartyOwner) pelo seu endereço de e-mail.
     *
     * @param email O e-mail do dono da festa.
     * @return Um Optional contendo o PartyOwner, se encontrado.
     */

    Optional<PartyOwner> findByEmail(String email);
}
