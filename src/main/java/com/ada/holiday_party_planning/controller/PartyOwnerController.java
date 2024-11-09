package com.ada.holiday_party_planning.controller;

import com.ada.holiday_party_planning.dto.*;
import com.ada.holiday_party_planning.service.PartyOwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Controlador REST para gerenciar proprietários de festas.
 * Define endpoints para registrar, autenticar, listar e atualizar proprietários de festas.
 */

@RestController
@RequestMapping("/party-owners")
@CrossOrigin("*")
public class PartyOwnerController {

    private final PartyOwnerService partyOwnerService;

    /**
     * Construtor que injeta o serviço de proprietários de festas.
     *
     * @param partyOwnerService Serviço para manipulação de dados de proprietários de festas.
     */

    public PartyOwnerController(PartyOwnerService partyOwnerService) {
        this.partyOwnerService = partyOwnerService;
    }

    /**
     * Registra um novo proprietário de festa.
     *
     * @param createPartyOwnerDTO Dados do proprietário de festa a ser criado.
     * @return Dados do proprietário criado e status 201 Created.
     */

    @PostMapping ("/register")
    public ResponseEntity<PartyOwnerDTO> createPartyOwner (@RequestBody CreatePartyOwnerDTO createPartyOwnerDTO) {

        PartyOwnerDTO partyOwnerDTO = partyOwnerService.createPartyOwner(createPartyOwnerDTO);

        return ResponseEntity
                .status(201)
                .body(partyOwnerDTO);
    }

    /**
     * Autentica um proprietário de festa.
     *
     * @param partyOwnerLoginDTO Dados de login do proprietário de festa.
     * @return Resposta com token de autenticação e informações do proprietário.
     */

    @PostMapping("/login")
    public ResponseEntity<PartyOwnerLoginResponseDTO> login(@RequestBody PartyOwnerLoginDTO partyOwnerLoginDTO) {
        PartyOwnerLoginResponseDTO partyOwnerLoginResponseDTO = partyOwnerService.login(partyOwnerLoginDTO);

        return ResponseEntity
                .ok(partyOwnerLoginResponseDTO);
    }

    /**
     * Retorna todos os proprietários de festas registrados.
     *
     * @return Lista de todos os proprietários de festas.
     */

    @GetMapping("/all")
    public ResponseEntity<List<PartyOwnerDTO>> getAllPartyOwners() {

        List<PartyOwnerDTO> allPartyOwners = partyOwnerService.getAllPartyOwners();

        return ResponseEntity.ok(allPartyOwners);
    }

    /**
     * Atualiza um proprietário de festa existente.
     *
     * @param partyOwnerDTO Dados atualizados do proprietário.
     * @param ownerId ID do proprietário de festa a ser atualizado.
     * @return Proprietário atualizado ou 404 caso não encontrado.
     */

    @PutMapping("/update/{ownerId}")
    public ResponseEntity<PartyOwnerDTO> updatePartyOwner(@RequestBody PartyOwnerDTO partyOwnerDTO, @PathVariable UUID ownerId) {

        Optional<PartyOwnerDTO> newPartyOwner = partyOwnerService.updatePartyOwner(ownerId, partyOwnerDTO);

        if(newPartyOwner.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(newPartyOwner.get());
        }
    }

}
