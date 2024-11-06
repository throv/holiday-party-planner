package com.ada.holiday_party_planning.repository;

import com.ada.holiday_party_planning.dto.PartyOwnerDTO;
import com.ada.holiday_party_planning.model.PartyOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface PartyOwnerRepository extends JpaRepository<PartyOwner,UUID>{

    Optional<PartyOwner> findByEmail(String email);
}
