package com.ada.holiday_party_planning.service;


import com.ada.holiday_party_planning.model.PartyOwner;
import com.ada.holiday_party_planning.repository.PartyOwnerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PartyOwnerRepository partyOwnerRepository;

    public CustomUserDetailsService(PartyOwnerRepository partyOwnerRepository) {
        this.partyOwnerRepository = partyOwnerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        PartyOwner partyOwner = partyOwnerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));

        return new User(
                partyOwner.getEmail(),
                partyOwner.getPassword(),
                new ArrayList<>()
        );
    }

}
