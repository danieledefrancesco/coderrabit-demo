package com.tuimm.learningpath.drivers;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
public class Slot {
    @NonNull
    private final LocalDateTime startDateTime;
    @NonNull
    private final LocalDateTime endDateTime;

    protected Slot(SlotBuilder<?,?> builder) {
        if (!builder.endDateTime.isAfter(builder.startDateTime)) {
            throw new IllegalArgumentException("startDateTime must be before endDateTime");
        }
        startDateTime = builder.startDateTime;
        endDateTime = builder.endDateTime;
    }

    public boolean clashesWith(@NonNull Slot other) {
        if (endDateTime.isBefore(other.startDateTime)) return false;
        return !startDateTime.isAfter(other.endDateTime);
    }
}
