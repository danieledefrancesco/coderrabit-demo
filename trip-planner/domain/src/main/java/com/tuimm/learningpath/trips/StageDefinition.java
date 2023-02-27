package com.tuimm.learningpath.trips;

import lombok.Builder;
import lombok.Getter;

import java.util.Comparator;
import java.util.UUID;

@Getter
@Builder
public class StageDefinition {
    private final String from;
    private final String to;
    private final Comparator<StagePlan> preferredPlanPolicy;
    private final UUID driverId;
}
