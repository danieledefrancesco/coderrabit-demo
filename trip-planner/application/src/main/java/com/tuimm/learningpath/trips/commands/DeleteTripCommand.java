package com.tuimm.learningpath.trips.commands;

import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.trips.TripsRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteTripCommand extends RequestHandler<DeleteTripRequest, Void> {
    private final TripsRepository repository;
    public DeleteTripCommand(TripsRepository repository) {
        super(DeleteTripRequest.class);
        this.repository = repository;
    }

    @Override
    public Void handle(DeleteTripRequest request) {
        repository.deleteById(request.getTripId());
        return null;
    }
}
