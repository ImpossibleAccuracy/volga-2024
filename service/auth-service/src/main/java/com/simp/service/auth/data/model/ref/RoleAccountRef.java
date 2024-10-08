package com.simp.service.auth.data.model.ref;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "\"Role_Account\"")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true, chain = true)
public class RoleAccountRef {
    @Column("account_id")
    private long account;

    @Column("role_id")
    private long role;
}
