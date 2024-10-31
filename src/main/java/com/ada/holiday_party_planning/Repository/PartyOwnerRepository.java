package com.ada.holiday_party_planning.Repository;

import com.ada.holiday_party_planning.model.PartyOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PartyOwnerRepository extends JpaRepository<PartyOwner,UUID>{ }
