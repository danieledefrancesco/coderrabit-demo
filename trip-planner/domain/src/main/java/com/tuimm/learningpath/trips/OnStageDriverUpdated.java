package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.common.Event;
import com.tuimm.learningpath.drivers.Driver;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OnStageDriverUpdated implements Event {
    private final Trip trip;
    private final StagePlan stagePlan;
    private final Driver oldDriver;
    private final Driver newDriver;
}
