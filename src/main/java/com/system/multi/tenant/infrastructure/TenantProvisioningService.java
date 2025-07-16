package com.system.multi.tenant.infrastructure;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TenantProvisioningService {

    private final JdbcTemplate jdbcTemplate;

    public TenantProvisioningService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTenant(String tenantId) {
        jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS " + tenantId);

        String createTableSql = "CREATE TABLE " + tenantId + ".client_tbl (" +
                "id BIGINT PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "email VARCHAR(255))";
        jdbcTemplate.execute(createTableSql);

    }
}
