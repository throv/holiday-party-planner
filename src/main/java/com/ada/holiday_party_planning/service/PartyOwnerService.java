package com.ada.holiday_party_planning.service;

import com.ada.holiday_party_planning.dto.CreatePartyOwnerDTO;
import com.ada.holiday_party_planning.dto.PartyOwnerDTO;
import com.ada.holiday_party_planning.dto.PartyOwnerLoginDTO;
import com.ada.holiday_party_planning.dto.PartyOwnerLoginResponseDTO;
import com.ada.holiday_party_planning.exceptions.EmailAlreadyExistsException;
import com.ada.holiday_party_planning.exceptions.InvalidCredentialsException;
import com.ada.holiday_party_planning.exceptions.PartyOwnerNotFoundException;
import com.ada.holiday_party_planning.mappers.PartyOwnerMapper;
import com.ada.holiday_party_planning.model.PartyOwner;
import com.ada.holiday_party_planning.repository.PartyOwnerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Serviço para gerenciar os proprietários de festas e suas lógicas de negócios, incluindo operações
 * para criação de proprietário, login, atualização e listagem de proprietários de festas.
 *
 * Este serviço utiliza criptografia de senha com BCrypt para garantir a segurança no armazenamento
 * e na verificação das credenciais dos proprietários.
 */

@Service
public class PartyOwnerService {

    private final PartyOwnerRepository partyOwnerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Construtor que inicializa o repositório e o codificador de senha.
     *
     * @param partyOwnerRepository Repositório para manipulação de proprietários de festas.
     */

    public PartyOwnerService(PartyOwnerRepository partyOwnerRepository) {
        this.partyOwnerRepository = partyOwnerRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Cria um novo proprietário de festa.
     *
     * Verifica se o e-mail informado já está em uso, lançando uma exceção se já existir um proprietário
     * com o mesmo e-mail. A senha é criptografada antes de ser armazenada.
     *
     * @param createPartyOwnerDTO DTO com as informações para criação de um novo proprietário de festa.
     * @return O DTO do proprietário de festa criado.
     * @throws EmailAlreadyExistsException Se o e-mail já estiver em uso.
     */

    public PartyOwnerDTO createPartyOwner(CreatePartyOwnerDTO createPartyOwnerDTO) {

        Optional<PartyOwner> existingPartyOwner = partyOwnerRepository.findByEmail(createPartyOwnerDTO.getEmail());

        if(existingPartyOwner.isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        PartyOwner partyOwnerCreated = PartyOwnerMapper.createDTOToModel(createPartyOwnerDTO);
        partyOwnerCreated.setPassword(
                passwordEncoder
                        .encode(
                        createPartyOwnerDTO
                                .getPassword()
                ));

        partyOwnerRepository.save(partyOwnerCreated);

        return PartyOwnerMapper.toDTO(partyOwnerCreated);

    }

    /**
     * Realiza o login de um proprietário de festa.
     *
     * Verifica se o proprietário existe com o e-mail informado e valida se a senha fornecida corresponde
     * à senha criptografada armazenada no banco. Retorna as informações de login do proprietário caso as credenciais
     * estejam corretas.
     *
     * @param userLoginInfo DTO contendo o e-mail e a senha para o login.
     * @return O DTO de resposta de login do proprietário.
     * @throws PartyOwnerNotFoundException Se o proprietário não for encontrado.
     * @throws InvalidCredentialsException Se as credenciais forem inválidas.
     */

    public PartyOwnerLoginResponseDTO login(PartyOwnerLoginDTO userLoginInfo) {
        Optional<PartyOwner> existingPartyOwner = partyOwnerRepository.findByEmail(userLoginInfo.getEmail());

        if(existingPartyOwner.isEmpty()) {
            throw new PartyOwnerNotFoundException();
        }

        PartyOwner partyOwner = existingPartyOwner.get();

        if(!passwordEncoder.matches(userLoginInfo.getPassword(), partyOwner.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return PartyOwnerMapper.toLoginResponseDTO(partyOwner);
    }

    /**
     * Retorna uma lista de todos os proprietários de festas cadastrados.
     *
     * @return Uma lista de DTOs dos proprietários de festas.
     * @throws PartyOwnerNotFoundException Se não houver nenhum proprietário cadastrado.
     */

    public List<PartyOwnerDTO> getAllPartyOwners() {
        List<PartyOwner> partyOwnersList = partyOwnerRepository.findAll();

        if(partyOwnersList.isEmpty()) throw new PartyOwnerNotFoundException();

        return PartyOwnerMapper.toDTOList(partyOwnersList);
    }

    /**
     * Atualiza as informações de um proprietário de festa.
     *
     * Recebe um DTO com as novas informações do proprietário e atualiza os dados no banco. Se o proprietário
     * não for encontrado, retorna uma opção vazia.
     *
     * @param ownerId O ID do proprietário a ser atualizado.
     * @param newPartyOwner O DTO com as novas informações do proprietário.
     * @return O DTO do proprietário atualizado, se encontrado.
     */

    public Optional<PartyOwnerDTO> updatePartyOwner(UUID ownerId, PartyOwnerDTO newPartyOwner) {

        Optional<PartyOwner> oldPartyOwner = partyOwnerRepository.findById(ownerId);

        if(oldPartyOwner.isPresent()) {
            PartyOwner existingPartyOwner = oldPartyOwner.get();
            PartyOwnerMapper.updatePartyOwnerDTO(newPartyOwner, existingPartyOwner);

            partyOwnerRepository.save(existingPartyOwner);

            return Optional.of(PartyOwnerMapper.toDTO(existingPartyOwner));
        }

        return Optional.empty();
    }


}
