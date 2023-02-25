package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.vehicles.*;
import org.springframework.stereotype.Service;

@Service
public class CreateScooterCommand extends RequestHandler<CreateScooterRequest, Vehicle> {
    private final VehicleFactory vehicleFactory;
    public CreateScooterCommand(VehicleFactory vehicleFactory) {
        super(CreateScooterRequest.class);
        this.vehicleFactory = vehicleFactory;
    }

    @Override
    public Vehicle handle(CreateScooterRequest request) {
        Scooter scooter = vehicleFactory.createScooter(builder ->
                builder.plate(ScooterPlate.from(request.getPlate()))
                        .fuelConsumption(request.getFuelConsumption())
                        .emissions(request.getEmissions())
                        .stopTimeInSeconds(request.getStopTimeInSeconds())
                        .fuelType(request.getFuelType())
                        .model(request.getModel())
                        .autonomy(request.getAutonomy())
                        .averageSpeed(request.getAverageSpeed())
                        .dailyRentPrice(request.getDailyRentPrice())
                        .maxPeople(request.getMaxPeople()));
        scooter.save();
        return scooter;
    }
}
