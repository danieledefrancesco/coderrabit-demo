package com.tuimm.learningpath.trips.queries;

import com.tuimm.learningpath.mediator.Request;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "create")
public class GetAllTripsRequest implements Request<GetAllTripsResponse> {
}
