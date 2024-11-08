package com.ada.holiday_party_planning.repository;

import com.ada.holiday_party_planning.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

/**
 * Interface de repositório para a entidade Item, estendendo a interface JpaRepository para fornecer
 * funcionalidades padrão de persistência de dados, como salvar, excluir, encontrar e listar itens.
 *
 * O Spring Data JPA automaticamente implementa essa interface para interagir com o banco de dados.
 */

public interface ItemRepository extends JpaRepository<Item, UUID> { }

