package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.vehicles.*;
import org.springframework.stereotype.Service;

@Service
public class CreateScooterCommand extends RequestHandler<CreateScooterRequest, Vehicle> {
    private final Garage garage;
    private final VehicleFactory vehicleFactory;
    public CreateScooterCommand(Garage garage, VehicleFactory vehicleFactory) {
        super(CreateScooterRequest.class);
        this.garage = garage;
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
        garage.addVehicle(scooter);
        return scooter;
    }
}
