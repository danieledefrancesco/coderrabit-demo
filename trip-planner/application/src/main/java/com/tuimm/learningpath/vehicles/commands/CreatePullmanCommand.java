package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.vehicles.*;
import org.springframework.stereotype.Service;

@Service
public class CreatePullmanCommand extends RequestHandler<CreatePullmanRequest, Vehicle> {
    private final VehicleFactory vehicleFactory;
    public CreatePullmanCommand(VehicleFactory vehicleFactory) {
        super(CreatePullmanRequest.class);
        this.vehicleFactory = vehicleFactory;
    }

    @Override
    public Vehicle handle(CreatePullmanRequest request) {
        Pullman pullman = vehicleFactory.createPullman(builder ->
                builder.plate(GenericPlate.from(request.getPlate()))
                        .fuelConsumption(request.getFuelConsumption())
                        .emissions(request.getEmissions())
                        .stopTimeInSeconds(request.getStopTimeInSeconds())
                        .fuelType(request.getFuelType())
                        .model(request.getModel())
                        .autonomy(request.getAutonomy())
                        .averageSpeed(request.getAverageSpeed())
                        .dailyRentPrice(request.getDailyRentPrice())
                        .maxPeople(request.getMaxPeople()));
        pullman.save();
        return pullman;
    }
}
