package com.ada.holiday_party_planning.repository;

import com.ada.holiday_party_planning.model.EventDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventDescriptionRepository extends JpaRepository<EventDescription, UUID> { }
