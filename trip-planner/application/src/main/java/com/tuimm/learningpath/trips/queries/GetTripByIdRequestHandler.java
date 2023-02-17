package com.tuimm.learningpath.trips.queries;

import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.trips.Trip;
import com.tuimm.learningpath.trips.TripsRepository;
import org.springframework.stereotype.Component;

@Component
public class GetTripByIdRequestHandler extends RequestHandler<GetTripByIdRequest, Trip> {
    private final TripsRepository repository;
    public GetTripByIdRequestHandler(TripsRepository repository) {
        super(GetTripByIdRequest.class);
        this.repository = repository;
    }

    @Override
    public Trip handle(GetTripByIdRequest request) {
        return repository.findById(request.getTripId());
    }
}
