package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.mediator.Request;
import com.tuimm.learningpath.vehicles.Vehicle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CreateVehicleRequest implements Request<Vehicle> {
    private String model;
    private int maxPeople;
    private double dailyRentPrice;
    private double autonomy;
    private double averageSpeed;
}
