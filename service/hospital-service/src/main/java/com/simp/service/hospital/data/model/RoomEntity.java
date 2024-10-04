package com.simp.service.hospital.data.model;

import com.simp.service.shared.domain.model.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "\"Room\"")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true, chain = true)
public class RoomEntity implements Room {
    @Id
    @Column("id")
    private long id;

    @Column("name")
    private String name;

    @Column("hospital_id")
    private long hospital;
}
