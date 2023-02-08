package com.tuimm.leaarningpath.domain.trips;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;

@Getter
@RequiredArgsConstructor(staticName = "create")
public class StageDefinition {
    private final String from;
    private final String to;
    private final Comparator<StagePlan> preferredPlanPolicy;
}
