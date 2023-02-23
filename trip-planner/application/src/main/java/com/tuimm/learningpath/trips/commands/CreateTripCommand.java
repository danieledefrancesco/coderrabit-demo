package com.tuimm.learningpath.trips.commands;

import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.trips.Trip;
import com.tuimm.learningpath.trips.TripFactory;
import com.tuimm.learningpath.trips.TripPlanner;
import com.tuimm.learningpath.trips.TripsRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTripCommand extends RequestHandler<CreateTripRequest, Trip> {
    private final TripFactory tripFactory;
    private final TripPlanner tripPlanner;
    private final TripsRepository repository;
    public CreateTripCommand(TripFactory tripFactory, TripPlanner tripPlanner, TripsRepository repository) {
        super(CreateTripRequest.class);
        this.tripFactory = tripFactory;
        this.tripPlanner = tripPlanner;
        this.repository = repository;
    }

    @Override
    public Trip handle(CreateTripRequest request) {
        Trip trip = tripFactory.create(tripPlanner.planTrip(request));
        repository.add(trip);
        return trip;
    }
}
