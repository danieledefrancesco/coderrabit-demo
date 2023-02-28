package com.tuimm.learningpath.trips.commands;

import com.tuimm.learningpath.drivers.commands.CreateDriverRequest;
import com.tuimm.learningpath.trips.StagePlan;
import lombok.Builder;
import lombok.Getter;

import java.util.Comparator;
import java.util.UUID;

@Getter
@Builder
public class CreateStageRequest {
    private final String from;
    private final String to;
    private final Comparator<StagePlan> preferredPlanPolicy;
    private final UUID driverId;
    private final CreateDriverRequest driver;
}
