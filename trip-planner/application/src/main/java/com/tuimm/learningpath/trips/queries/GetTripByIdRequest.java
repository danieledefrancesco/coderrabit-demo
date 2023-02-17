package com.tuimm.learningpath.trips.queries;

import com.tuimm.learningpath.mediator.Request;
import com.tuimm.learningpath.trips.Trip;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor(staticName = "create")
@Getter
public class GetTripByIdRequest implements Request<Trip> {
    private final UUID tripId;
}
