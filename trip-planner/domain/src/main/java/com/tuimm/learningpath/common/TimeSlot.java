package com.tuimm.learningpath.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
@EqualsAndHashCode
public class TimeSlot {
    @NonNull
    private final LocalDateTime startDateTime;
    @NonNull
    private final LocalDateTime endDateTime;

    protected TimeSlot(TimeSlotBuilder<?,?> builder) {
        if (!builder.endDateTime.isAfter(builder.startDateTime)) {
            throw new IllegalArgumentException("startDateTime must be before endDateTime");
        }
        startDateTime = builder.startDateTime;
        endDateTime = builder.endDateTime;
    }

    public boolean clashesWith(@NonNull TimeSlot other) {
        if (endDateTime.isBefore(other.startDateTime)) return false;
        return !startDateTime.isAfter(other.endDateTime);
    }
}
