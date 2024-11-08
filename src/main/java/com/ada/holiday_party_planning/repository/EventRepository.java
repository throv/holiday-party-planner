package com.ada.holiday_party_planning.repository;

import com.ada.holiday_party_planning.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Interface de repositório para a entidade Event, estendendo a interface JpaRepository para fornecer
 * funcionalidades padrão de persistência de dados, como salvar, excluir, encontrar e listar eventos.
 *
 * O Spring Data JPA automaticamente implementa essa interface para interagir com o banco de dados.
 */

public interface EventRepository extends JpaRepository <Event,UUID> { }
