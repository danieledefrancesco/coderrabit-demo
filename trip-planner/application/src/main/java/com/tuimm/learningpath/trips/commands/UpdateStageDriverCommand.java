package com.tuimm.learningpath.trips.commands;

import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.drivers.DriversRepository;
import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.trips.Trip;
import com.tuimm.learningpath.trips.TripsRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateStageDriverCommand extends RequestHandler<UpdateStageDriverRequest, Void> {
    private final TripsRepository tripsRepository;
    private final DriversRepository driversRepository;
    public UpdateStageDriverCommand(TripsRepository tripsRepository, DriversRepository driversRepository) {
        super(UpdateStageDriverRequest.class);
        this.tripsRepository = tripsRepository;
        this.driversRepository = driversRepository;
    }

    @Override
    public Void handle(UpdateStageDriverRequest request) {
        Trip trip = tripsRepository.findById(request.getTripId());
        Driver driver = driversRepository.findById(request.getDriverId());
        trip.updateStageDriver(request.getTimeSlot(), driver);
        trip.save();
        return null;
    }
}
