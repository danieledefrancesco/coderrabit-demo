package com.tuimm.learningpath.trips.commands;

import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.trips.Trip;
import com.tuimm.learningpath.trips.TripFactory;
import com.tuimm.learningpath.trips.TripPlanner;
import org.springframework.stereotype.Service;

@Service
public class CreateTripCommand extends RequestHandler<CreateTripRequest, Trip> {
    private final TripFactory tripFactory;
    private final TripPlanner tripPlanner;
    public CreateTripCommand(TripFactory tripFactory, TripPlanner tripPlanner) {
        super(CreateTripRequest.class);
        this.tripFactory = tripFactory;
        this.tripPlanner = tripPlanner;
    }

    @Override
    public Trip handle(CreateTripRequest request) {
        Trip trip = tripFactory.create(tripPlanner.planTrip(request));
        trip.save();
        return trip;
    }
}
