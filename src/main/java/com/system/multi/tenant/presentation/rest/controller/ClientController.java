package com.system.multi.tenant.presentation.rest.controller;

import com.system.multi.tenant.application.dto.ClientResponse;
import com.system.multi.tenant.application.dto.CreateClientRequest;
import com.system.multi.tenant.application.service.ClientApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientApplicationService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@RequestBody CreateClientRequest request) {
        ClientResponse response = clientService.createClient(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable Long id) {
        return clientService.findClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        List<ClientResponse> clients = clientService.findAllClients();
        return ResponseEntity.ok(clients);
    }
}
