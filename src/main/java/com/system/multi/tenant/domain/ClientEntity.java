package com.system.multi.tenant.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    private Long id;
    private String name;
    private String email;
}
