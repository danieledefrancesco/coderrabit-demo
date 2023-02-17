package com.tuimm.learningpath.trips.queries;

import com.tuimm.learningpath.trips.Trip;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@Getter
@RequiredArgsConstructor(staticName = "create")
public class GetAllTripsResponse {
    private final Collection<Trip> trips;
}
