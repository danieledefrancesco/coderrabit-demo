package com.tuimm.learningpath.application.vehicles.commands;

import com.tuimm.learningpath.common.mediator.RequestHandler;
import com.tuimm.learningpath.domain.vehicles.Bike;
import com.tuimm.learningpath.domain.vehicles.Garage;
import com.tuimm.learningpath.domain.vehicles.Vehicle;
import com.tuimm.learningpath.domain.vehicles.VehicleFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateBikeCommandRequestHandler extends RequestHandler<CreateBikeRequest, Vehicle> {
    private final VehicleFactory vehicleFactory;
    private final Garage garage;
    public CreateBikeCommandRequestHandler(VehicleFactory vehicleFactory, Garage garage) {
        super(CreateBikeRequest.class);
        this.vehicleFactory = vehicleFactory;
        this.garage = garage;
    }

    @Override
    public Vehicle handle(CreateBikeRequest request) {
        Bike bike = vehicleFactory.createBike(builder ->
                builder.model(request.getModel())
                        .maxPeople(request.getMaxPeople())
                        .dailyRentPrice(request.getDailyRentPrice())
                        .averageSpeed(request.getAverageSpeed())
                        .autonomy(request.getAutonomy()));
        garage.addVehicle(bike);
        return bike;
    }
}
