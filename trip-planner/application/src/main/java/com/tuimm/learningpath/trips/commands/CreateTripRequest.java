package com.tuimm.learningpath.trips.commands;

import com.tuimm.learningpath.mediator.Request;
import com.tuimm.learningpath.trips.StageDefinition;
import com.tuimm.learningpath.trips.Trip;
import com.tuimm.learningpath.trips.TripDefinition;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
public class CreateTripRequest extends TripDefinition implements Request<Trip> {
    protected CreateTripRequest(LocalDateTime start, List<StageDefinition> stages, int numberOfPeople) {
        super(start, stages, numberOfPeople);
    }
}
