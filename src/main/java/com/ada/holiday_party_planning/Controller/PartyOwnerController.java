package com.ada.holiday_party_planning.Controller;

import com.ada.holiday_party_planning.model.PartyOwner;
import org.springframework.web.bind.annotation.*;
import com.ada.holiday_party_planning.Repository.PartyOwnerRepository;

import java.util.List;

@RestController
public class PartyOwnerController {

    private final PartyOwnerRepository partyOwnerRepository;

    public PartyOwnerController(PartyOwnerRepository partyOwnerRepository) {
        this.partyOwnerRepository = partyOwnerRepository;
    }

    @GetMapping ("/usuario")
    public List<PartyOwner> listPartyOwner () {
        return (List<PartyOwner>) partyOwnerRepository.findAll();
    }

    @GetMapping ("/teste")
    public String teste () {
        return "Teste";
    }

    @PutMapping
    public PartyOwner updatePartyOwner (@RequestBody PartyOwner partyOwner) {
        return  partyOwnerRepository.save(partyOwner);
    }

    @PostMapping ("/cadastrarusuario")
    public PartyOwner creatPartyOwner (@RequestBody PartyOwner partyOwner) {
        return  partyOwnerRepository.save(partyOwner);
    }

}
