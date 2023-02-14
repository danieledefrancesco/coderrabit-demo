package com.tuimm.learningpath.application.vehicles.commands;

import com.tuimm.learningpath.common.mediator.RequestHandler;
import com.tuimm.learningpath.domain.vehicles.*;
import org.springframework.stereotype.Component;

@Component
public class CreateCarCommandRequestHandler extends RequestHandler<CreateCarRequest, Vehicle> {
    private final Garage garage;
    private final VehicleFactory vehicleFactory;
    public CreateCarCommandRequestHandler(Garage garage, VehicleFactory vehicleFactory) {
        super(CreateCarRequest.class);
        this.garage = garage;
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
        garage.addVehicle(car);
        return car;
    }
}
