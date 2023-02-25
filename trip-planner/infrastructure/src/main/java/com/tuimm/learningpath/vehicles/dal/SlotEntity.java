package com.tuimm.learningpath.vehicles.dal;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@Table(name = "vehicles_reserved_slots")
public class SlotEntity {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
