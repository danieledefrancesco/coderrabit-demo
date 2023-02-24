package com.tuimm.learningpath.drivers.dal;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@Table(name = "slots")
public class SlotEntity {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
