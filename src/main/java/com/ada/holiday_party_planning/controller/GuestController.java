package com.ada.holiday_party_planning.controller;

import com.ada.holiday_party_planning.dto.CreateGuestDTO;
import com.ada.holiday_party_planning.dto.GuestDTO;
import com.ada.holiday_party_planning.mappers.GuestMapper;
import com.ada.holiday_party_planning.model.Guest;
import com.ada.holiday_party_planning.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Controlador REST para gerenciar convidados.
 * Define endpoints para criar, atualizar, listar e excluir convidados.
 */

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private GuestService guestService;

    /**
     * Retorna um convidado específico com base no ID.
     *
     * @param guestId ID do convidado.
     * @return Convidado correspondente ao ID ou 404 caso não encontrado.
     */

    @GetMapping("/{guestId}")
    public ResponseEntity<GuestDTO> getGuestById(@PathVariable UUID guestId) {

        Optional<GuestDTO> guestDTO = guestService.getGuestById(guestId);

        if (guestDTO.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(guestDTO.get());
        }
    }

    /**
     * Retorna todos os convidados cadastrados.
     *
     * @return Lista de todos os convidados ou mensagem de erro caso não haja dados.
     */

    @GetMapping("/all")
    public ResponseEntity<?> getAllGuests() {

        List<GuestDTO> allGuests = guestService.getAllGuests();

        if (allGuests.isEmpty()) {
            return ResponseEntity.status(404).body("Message: No information found!");
        }

        return ResponseEntity.ok(allGuests);
    }

    /**
     * Cria um novo convidado.
     *
     * @param createGuestDTO Dados do convidado a ser criado.
     * @return Convidado criado e status 201 Created.
     */

    @PostMapping("/create")
    public ResponseEntity<GuestDTO> createGuest(@RequestBody CreateGuestDTO createGuestDTO) {

        Guest guest = guestService.createGuest(createGuestDTO);
        GuestDTO guestDTO = GuestMapper.toDTO(guest);
        return ResponseEntity.status(201).body(guestDTO);
    }

    /**
     * Atualiza um convidado existente.
     *
     * @param guestDTO Dados atualizados do convidado.
     * @param guestId ID do convidado a ser atualizado.
     * @return Convidado atualizado ou 404 caso não encontrado.
     */

    @PutMapping("/update/{guestId}")
    public ResponseEntity<GuestDTO> updateGuest(@RequestBody GuestDTO guestDTO, @PathVariable UUID guestId) {

        Optional<GuestDTO> newGuest = guestService.updateGuest(guestId, guestDTO);

        if (newGuest.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(newGuest.get());
        }
    }

    /**
     * Exclui um convidado com base no ID fornecido.
     *
     * @param guestId ID do convidado a ser excluído.
     * @return Mensagem de sucesso ao excluir o convidado ou 404 caso não encontrado.
     */

    @DeleteMapping("/delete/{guestId}")
    public ResponseEntity<String> deleteGuest(@PathVariable UUID guestId) {

        guestService.deleteGuest(guestId);
        return ResponseEntity.status(204).body("Message: Guest deleted successfully!");
    }
}
