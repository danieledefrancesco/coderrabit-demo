package com.tuimm.learningpath.trips.commands;

import com.tuimm.learningpath.mediator.Request;
import com.tuimm.learningpath.trips.Trip;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@Getter
public class CreateTripRequest implements Request<Trip> {
    private final LocalDateTime start;
    private final List<CreateStageRequest> stages;
    private final int numberOfPeople;
}
