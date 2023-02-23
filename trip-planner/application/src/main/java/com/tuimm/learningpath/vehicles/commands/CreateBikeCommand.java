package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.vehicles.Bike;
import com.tuimm.learningpath.vehicles.Garage;
import com.tuimm.learningpath.vehicles.Vehicle;
import com.tuimm.learningpath.vehicles.VehicleFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateBikeCommand extends RequestHandler<CreateBikeRequest, Vehicle> {
    private final VehicleFactory vehicleFactory;
    private final Garage garage;
    public CreateBikeCommand(VehicleFactory vehicleFactory, Garage garage) {
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
