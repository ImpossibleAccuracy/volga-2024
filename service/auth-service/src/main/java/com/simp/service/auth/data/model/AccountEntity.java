package com.simp.service.auth.data.model;

import com.simp.service.shared.data.model.BaseEntity;
import com.simp.service.shared.domain.model.Account;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "\"Account\"")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true, chain = true)
public class AccountEntity extends BaseEntity implements Account {
    @Column("username")
    private String username;

    @Column("password")
    private String password;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("deleted")
    @Builder.Default
    private boolean deleted = false;
}
