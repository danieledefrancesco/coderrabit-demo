package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.common.Aggregate;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
public class Trip extends Aggregate<Trip> {
    private final UUID id;
    private final TripPlan plan;

    @Override
    public void delete() {
        super.delete();
        addEvent(OnTripDeletedEvent.of(this));
    }
}
