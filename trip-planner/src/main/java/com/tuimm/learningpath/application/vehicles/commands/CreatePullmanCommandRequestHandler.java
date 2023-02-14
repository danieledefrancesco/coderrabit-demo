package com.tuimm.learningpath.application.vehicles.commands;

import com.tuimm.learningpath.common.mediator.RequestHandler;
import com.tuimm.learningpath.domain.vehicles.*;
import org.springframework.stereotype.Component;

@Component
public class CreatePullmanCommandRequestHandler extends RequestHandler<CreatePullmanRequest, Vehicle> {
    private final Garage garage;
    private final VehicleFactory vehicleFactory;
    public CreatePullmanCommandRequestHandler(Garage garage, VehicleFactory vehicleFactory) {
        super(CreatePullmanRequest.class);
        this.garage = garage;
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
        garage.addVehicle(pullman);
        return pullman;
    }
}
