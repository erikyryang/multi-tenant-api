package com.system.multi.tenant.application.dto;


import com.system.multi.tenant.domain.ClientEntity;

public record ClientResponse(Long id, String name, String email) {
    public static ClientResponse fromEntity(ClientEntity client) {
        return new ClientResponse(client.getId(), client.getName(), client.getEmail());
    }
}