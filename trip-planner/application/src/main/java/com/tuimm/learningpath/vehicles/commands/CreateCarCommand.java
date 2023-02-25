package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.vehicles.*;
import org.springframework.stereotype.Service;

@Service
public class CreateCarCommand extends RequestHandler<CreateCarRequest, Vehicle> {
    private final VehicleFactory vehicleFactory;
    public CreateCarCommand(VehicleFactory vehicleFactory) {
        super(CreateCarRequest.class);
        this.vehicleFactory = vehicleFactory;
    }

    @Override
    public Vehicle handle(CreateCarRequest request) {
        Car car = vehicleFactory.createCar(builder ->
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
        car.save();
        return car;
    }
}
