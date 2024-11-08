package com.ada.holiday_party_planning.mappers;

import com.ada.holiday_party_planning.dto.CreatePartyOwnerDTO;
import com.ada.holiday_party_planning.dto.PartyOwnerDTO;
import com.ada.holiday_party_planning.dto.PartyOwnerLoginResponseDTO;
import com.ada.holiday_party_planning.model.PartyOwner;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe responsável por realizar a conversão entre objetos DTO (Data Transfer Object) e entidades
 * relacionadas a um dono de festa (PartyOwner), facilitando a transferência de dados entre as camadas
 * de controle e serviço da aplicação. Ela oferece métodos para transformar objetos de PartyOwner entre
 * suas representações em DTOs e entidades.
 */

public class PartyOwnerMapper {

    /**
     * Converte um objeto PartyOwner (entidade) em um objeto PartyOwnerDTO (Data Transfer Object).
     *
     * @param partyOwner O objeto PartyOwner a ser convertido.
     * @return Um objeto PartyOwnerDTO representando o dono da festa.
     */

    public static PartyOwnerDTO toDTO(PartyOwner partyOwner) {
        return new PartyOwnerDTO(
                partyOwner.getOwnerId(),
                partyOwner.getName(),
                partyOwner.getEmail()
        );
    }

    /**
     * Converte um objeto PartyOwner (entidade) em um objeto CreatePartyOwnerDTO (Data Transfer Object)
     * para ser usado na criação de um novo PartyOwner.
     *
     * @param partyOwner O objeto PartyOwner a ser convertido.
     * @return Um objeto CreatePartyOwnerDTO representando os dados necessários para criar um PartyOwner.
     */

    public static CreatePartyOwnerDTO toCreatePartyOwnerDTO(PartyOwner partyOwner) {
        return new CreatePartyOwnerDTO(
                partyOwner.getName(),
                partyOwner.getEmail(),
                partyOwner.getPassword()
                );
    }

    /**
     * Converte um CreatePartyOwnerDTO (Data Transfer Object) em um objeto PartyOwner (entidade).
     *
     * @param createPartyOwnerDTO O objeto CreatePartyOwnerDTO a ser convertido.
     * @return Um objeto PartyOwner representando o dono da festa.
     */

    public static PartyOwner createDTOToModel(CreatePartyOwnerDTO createPartyOwnerDTO) {
        return new PartyOwner(
                createPartyOwnerDTO.getName(),
                createPartyOwnerDTO.getEmail(),
                createPartyOwnerDTO.getPassword()
        );
    }

    /**
     * Converte um objeto PartyOwnerDTO (Data Transfer Object) em um objeto PartyOwner (entidade).
     *
     * @param partyOwnerDTO O objeto PartyOwnerDTO a ser convertido.
     * @return Um objeto PartyOwner representando o dono da festa.
     */

    public static PartyOwner toModel(PartyOwnerDTO partyOwnerDTO) {

        return new PartyOwner(
                partyOwnerDTO.getName(),
                partyOwnerDTO.getEmail(),
                null
        );
    }

    /**
     * Converte um objeto PartyOwner (entidade) em um objeto PartyOwnerLoginResponseDTO (Data Transfer Object).
     * Este DTO é utilizado para a resposta de login do dono da festa.
     *
     * @param partyOwner O objeto PartyOwner a ser convertido.
     * @return Um objeto PartyOwnerLoginResponseDTO representando a resposta do login do dono da festa.
     */

    public static PartyOwnerLoginResponseDTO toLoginResponseDTO(PartyOwner partyOwner) {

        return new PartyOwnerLoginResponseDTO(
                partyOwner.getName(),
                partyOwner.getEmail()
        );
    }

    /**
     * Converte uma lista de objetos PartyOwner (entidades) em uma lista de objetos PartyOwnerDTO (Data Transfer Objects).
     *
     * @param partyOwnerList A lista de PartyOwner a ser convertida.
     * @return Uma lista de objetos PartyOwnerDTO representando os donos de festa.
     */

    public static List<PartyOwnerDTO> toDTOList(List<PartyOwner> partyOwnerList) {
        return partyOwnerList.stream()
                .map(PartyOwnerMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Atualiza os dados de um PartyOwner a partir de um PartyOwnerDTO.
     *
     * @param ownerDTO O objeto PartyOwnerDTO com os dados atualizados.
     * @param partyOwner O objeto PartyOwner a ser atualizado.
     */

    public static void updatePartyOwnerDTO(PartyOwnerDTO ownerDTO, PartyOwner partyOwner) {
        partyOwner.setName(ownerDTO.getName());
        partyOwner.setEmail(ownerDTO.getEmail());
    }
}
