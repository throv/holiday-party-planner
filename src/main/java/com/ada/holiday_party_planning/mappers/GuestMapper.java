package com.ada.holiday_party_planning.mappers;

import com.ada.holiday_party_planning.dto.CreateGuestDTO;
import com.ada.holiday_party_planning.dto.GuestDTO;
import com.ada.holiday_party_planning.model.Event;
import com.ada.holiday_party_planning.model.Guest;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável por realizar a conversão entre objetos DTO (Data Transfer Object) e entidades
 * relacionadas a convidados e eventos, facilitando a transferência de dados entre as camadas de controle e serviço da aplicação.
 * Ela oferece métodos para transformar objetos de convidados entre suas representações em DTOs e entidades.
 */

public class GuestMapper {

    /**
     * Converte um objeto Guest (entidade) em um objeto GuestDTO (Data Transfer Object).
     *
     * @param guest O objeto Guest a ser convertido.
     * @return Um objeto GuestDTO representando o convidado.
     */

    public static GuestDTO toDTO(Guest guest) {

        return new GuestDTO(
            guest.getGuestId(),
            guest.getName(),
            guest.getEmail(),
            guest.getStatus()
        );
    }

    /**
     * Converte um objeto GuestDTO (Data Transfer Object) em um objeto Guest (entidade).
     *
     * @param guestDTO O objeto GuestDTO a ser convertido.
     * @param event O evento associado ao convidado.
     * @return Um objeto Guest representando a entidade do convidado.
     */

    public static Guest toEntity(GuestDTO guestDTO, Event event) {

        Guest guest = new Guest();
        guest.setGuestId(guestDTO.getGuestId());
        guest.setName(guestDTO.getName());
        guest.setEmail(guestDTO.getEmail());
        guest.setStatus(guestDTO.getStatus());
        guest.setEvent(event);
        return guest;
    }

    /**
     * Converte uma lista de objetos Guest (entidades) em uma lista de objetos GuestDTO (Data Transfer Objects).
     *
     * @param guestsList A lista de convidados (Guest) a ser convertida.
     * @return Uma lista de objetos GuestDTO representando os convidados.
     */

    public static List<GuestDTO> toDTOList(List<Guest> guestsList) {
        return guestsList.stream().map(GuestMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Converte um objeto CreateGuestDTO (Data Transfer Object) para um objeto Guest (entidade).
     *
     * @param createGuestDTO O DTO de criação de convidado com os dados necessários.
     * @return Um objeto Guest representando a entidade do convidado a ser criado.
     */

    public static Guest createDTOToModel(CreateGuestDTO createGuestDTO) {
        Guest guest = new Guest();
        guest.setName(createGuestDTO.getName());
        guest.setEmail(createGuestDTO.getEmail());
        guest.setStatus(createGuestDTO.getStatus());
        guest.setConfirmed(createGuestDTO.isConfirmed());
        guest.setEvent(createGuestDTO.getEvent());
        return guest;
    }

    /**
     * Atualiza os dados de um objeto Guest com os valores fornecidos por um GuestDTO.
     *
     * @param guestDTO O DTO contendo os novos dados do convidado.
     * @param guest O objeto Guest a ser atualizado.
     */

    public static void updateGuestDTO(GuestDTO guestDTO, Guest guest) {
        guest.setName(guestDTO.getName());
        guest.setEmail(guestDTO.getEmail());
        guest.setStatus(guestDTO.getStatus());
    }
}
