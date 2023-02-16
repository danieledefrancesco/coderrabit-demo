package com.tuimm.learningpath.trips;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class Trip {
    UUID id;
    TripPlan plan;
}
