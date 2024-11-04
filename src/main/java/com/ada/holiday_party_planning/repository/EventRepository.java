package com.ada.holiday_party_planning.repository;

import com.ada.holiday_party_planning.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository <Event,UUID> { }
