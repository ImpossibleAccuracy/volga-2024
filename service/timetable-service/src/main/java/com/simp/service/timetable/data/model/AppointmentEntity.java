package com.simp.service.timetable.data.model;

import com.simp.service.shared.data.model.BaseAuditEntity;
import com.simp.service.shared.domain.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table(name = "\"Appointment\"")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true, chain = true)
public class AppointmentEntity extends BaseAuditEntity implements Appointment {
    @Column("timetable_id")
    private long timetable;

    @Column("time")
    private Instant time;

    @Column("deleted")
    private boolean deleted;
}
