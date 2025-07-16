package com.system.multi.tenant.presentation.rest.controller;

import com.system.multi.tenant.infrastructure.TenantProvisioningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tenants")
public class TenantController {

    private final TenantProvisioningService provisioningService;

    @PostMapping("/{tenantId}")
    public ResponseEntity<String> createTenant(@PathVariable String tenantId) {
        provisioningService.createTenant(tenantId);
        return ResponseEntity.ok("Tenant " + tenantId + " provisionado com sucesso.");
    }
}