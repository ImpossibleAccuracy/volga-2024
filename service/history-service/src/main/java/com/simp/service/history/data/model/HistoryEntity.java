package com.simp.service.history.data.model;


import com.simp.service.shared.data.model.BaseAuditEntity;
import com.simp.service.shared.domain.model.History;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table(name = "\"History\"")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true, chain = true)
public class HistoryEntity extends BaseAuditEntity implements History {
    @Column("date")
    private Instant date;

    @Column("patient")
    private long patient;

    @Column("hospital")
    private long hospital;

    @Column("doctor")
    private long doctor;

    @Column("rom")
    private long room;

    @Column("data")
    private String data;
}
