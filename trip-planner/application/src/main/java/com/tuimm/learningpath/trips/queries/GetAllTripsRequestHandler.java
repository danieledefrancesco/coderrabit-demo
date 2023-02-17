package com.tuimm.learningpath.trips.queries;

import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.trips.TripsRepository;
import org.springframework.stereotype.Component;

@Component
public class GetAllTripsRequestHandler extends RequestHandler<GetAllTripsRequest, GetAllTripsResponse> {
    private final TripsRepository repository;

    public GetAllTripsRequestHandler(TripsRepository repository) {
        super(GetAllTripsRequest.class);
        this.repository = repository;
    }

    @Override
    public GetAllTripsResponse handle(GetAllTripsRequest request) {
        return GetAllTripsResponse.create(repository.findAll());
    }
}
