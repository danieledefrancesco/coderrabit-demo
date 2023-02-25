package com.tuimm.learningpath.trips.commands;

import com.tuimm.learningpath.common.TimeSlot;
import com.tuimm.learningpath.mediator.Request;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class UpdateStageDriverRequest implements Request<Void> {
    private final UUID tripId;
    private final TimeSlot timeSlot;
    private final UUID driverId;
}
