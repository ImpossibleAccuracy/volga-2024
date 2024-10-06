package com.simp.service.timetable.data.model;

import com.simp.service.shared.data.model.BaseAuditEntity;
import com.simp.service.shared.domain.model.Timetable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table(name = "\"Timetable\"")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true, chain = true)
public class TimetableEntity extends BaseAuditEntity implements Timetable {
    @Column("hospital_id")
    private long hospital;

    @Column("doctor_id")
    private long doctor;

    @Column("room_id")
    private long room;

    @Column("date_from")
    private Instant from;

    @Column("date_to")
    private Instant to;

    @Column("deleted")
    private boolean deleted;
}
