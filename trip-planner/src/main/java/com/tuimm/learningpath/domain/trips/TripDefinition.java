package com.tuimm.learningpath.domain.trips;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor(staticName = "create")
public class TripDefinition {
    private final LocalDateTime start;
    private final List<StageDefinition> stages;
    private final int numberOfPeople;
}
