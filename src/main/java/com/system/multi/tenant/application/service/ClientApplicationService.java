package com.system.multi.tenant.application.service;

import com.system.multi.tenant.application.dto.ClientResponse;
import com.system.multi.tenant.application.dto.CreateClientRequest;
import com.system.multi.tenant.domain.ClientEntity;
import com.system.multi.tenant.domain.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientApplicationService {

    private final ClientRepository clientRepository;

    @Transactional
    public ClientResponse createClient(CreateClientRequest request) {
        ClientEntity newClient = new ClientEntity(request.id(), request.name(), request.email());
        ClientEntity savedClient = clientRepository.save(newClient);
        return ClientResponse.fromEntity(savedClient);
    }

    @Transactional(readOnly = true)
    public Optional<ClientResponse> findClientById(Long id) {
        return clientRepository.findById(id).map(ClientResponse::fromEntity);
    }

    @Transactional(readOnly = true)
    public List<ClientResponse> findAllClients() {
        return clientRepository.findAll().stream()
                .map(ClientResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
