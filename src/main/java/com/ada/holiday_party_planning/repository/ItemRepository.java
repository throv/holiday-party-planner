package com.ada.holiday_party_planning.repository;

import com.ada.holiday_party_planning.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ItemRepository extends JpaRepository<Item, UUID> { }

