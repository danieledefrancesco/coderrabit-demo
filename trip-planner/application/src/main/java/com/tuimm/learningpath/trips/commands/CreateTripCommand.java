package com.tuimm.learningpath.trips.commands;

import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.drivers.queries.GetDriverByIdRequest;
import com.tuimm.learningpath.mediator.Mediator;
import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.trips.*;
import org.springframework.stereotype.Service;

@Service
public class CreateTripCommand extends RequestHandler<CreateTripRequest, Trip> {
    private final TripFactory tripFactory;
    private final TripPlanner tripPlanner;
    private final Mediator mediator;
    public CreateTripCommand(TripFactory tripFactory, TripPlanner tripPlanner, Mediator mediator) {
        super(CreateTripRequest.class);
        this.tripFactory = tripFactory;
        this.tripPlanner = tripPlanner;
        this.mediator = mediator;
    }

    @Override
    public Trip handle(CreateTripRequest request) {
        Trip trip = tripFactory.create(tripPlanner.planTrip(toTripDefinition(request)));
        trip.save();
        return trip;
    }

    private TripDefinition toTripDefinition(CreateTripRequest createTripRequest) {
        return TripDefinition.builder()
                .stages(createTripRequest.getStages().stream().map(this::toStageDefinition).toList())
                .start(createTripRequest.getStart())
                .numberOfPeople(createTripRequest.getNumberOfPeople())
                .build();
    }

    private StageDefinition toStageDefinition(CreateStageRequest createStageRequest) {
        Driver driver = createStageRequest.getDriverId() != null ?
                mediator.send(GetDriverByIdRequest.fromId(createStageRequest.getDriverId())) :
                mediator.send(createStageRequest.getDriver());
        return StageDefinition.builder()
                .from(createStageRequest.getFrom())
                .to(createStageRequest.getTo())
                .driverId(driver.getId())
                .preferredPlanPolicy(createStageRequest.getPreferredPlanPolicy())
                .build();
    }
}
