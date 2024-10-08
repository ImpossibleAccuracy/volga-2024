package com.simp.service.hospital.data.model;

import com.simp.service.shared.data.model.BaseAuditEntity;
import com.simp.service.shared.domain.model.Hospital;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "\"Hospital\"")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true, chain = true)
public class HospitalEntity extends BaseAuditEntity implements Hospital {
    @Column("name")
    private String name;

    @Column("address")
    private String address;

    @Column("contact_phone")
    private String contactPhone;

    @Column("deleted")
    @Builder.Default
    private boolean deleted = false;
}
