package com.ada.holiday_party_planning.repository;

import com.ada.holiday_party_planning.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GuestRepository extends JpaRepository<Guest, UUID> {

    Optional<Guest> findByEmail(String email);
}
