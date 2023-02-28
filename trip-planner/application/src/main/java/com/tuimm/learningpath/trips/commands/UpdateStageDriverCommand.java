package com.tuimm.learningpath.trips.commands;

import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.drivers.queries.GetDriverByIdRequest;
import com.tuimm.learningpath.mediator.Mediator;
import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.trips.Trip;
import com.tuimm.learningpath.trips.TripsRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateStageDriverCommand extends RequestHandler<UpdateStageDriverRequest, Void> {
    private final TripsRepository tripsRepository;
    private final Mediator mediator;

    public UpdateStageDriverCommand(TripsRepository tripsRepository, Mediator mediator) {
        super(UpdateStageDriverRequest.class);
        this.tripsRepository = tripsRepository;
        this.mediator = mediator;
    }

    @Override
    public Void handle(UpdateStageDriverRequest request) {
        Trip trip = tripsRepository.findById(request.getTripId());
        Driver driver = request.getDriverId() != null ?
                mediator.send(GetDriverByIdRequest.fromId(request.getDriverId())) :
                mediator.send(request.getCreateDriverRequest());
        trip.updateStageDriver(request.getTimeSlot(), driver);
        trip.save();
        return null;
    }
}
