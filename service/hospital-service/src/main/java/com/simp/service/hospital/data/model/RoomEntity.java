package com.simp.service.hospital.data.model;

import com.simp.service.shared.data.model.BaseEntity;
import com.simp.service.shared.domain.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "\"Room\"")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true, chain = true)
public class RoomEntity extends BaseEntity implements Room {
    @Column("name")
    private String name;

    @Column("hospital_id")
    private long hospital;
}
