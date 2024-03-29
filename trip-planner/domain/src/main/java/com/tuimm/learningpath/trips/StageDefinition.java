package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.drivers.Driver;
import lombok.Builder;
import lombok.Getter;

import java.util.Comparator;

@Getter
@Builder
public class StageDefinition {
    private final String from;
    private final String to;
    private final Comparator<StagePlan> preferredPlanPolicy;
    private final Driver driver;
}
