package com.simp.service.hospital.data.model;

import com.simp.service.shared.domain.model.Hospital;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table(name = "\"Hospital\"")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true, chain = true)
public class HospitalEntity implements Hospital {
    @Id
    @Column("id")
    private long id;

    @Column("name")
    private String name;

    @Column("address")
    private String address;

    @Column("contact_phone")
    private String contactPhone;

    @Column("deleted")
    @Builder.Default
    private boolean deleted = false;

    @Column("created_at")
    @CreatedDate
    private Instant createdAt;

    @Column("creator_id")
    @CreatedBy
    private long creator;
}
