package com.tuimm.learningpath.trips.commands;

import com.tuimm.learningpath.mediator.Request;
import com.tuimm.learningpath.trips.Trip;
import com.tuimm.learningpath.trips.TripDefinition;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CreateTripRequest extends TripDefinition implements Request<Trip> {
}
