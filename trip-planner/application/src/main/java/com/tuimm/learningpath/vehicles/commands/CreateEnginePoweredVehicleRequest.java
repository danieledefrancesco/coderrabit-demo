package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.vehicles.FuelType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CreateEnginePoweredVehicleRequest extends CreateVehicleRequest {
    private int stopTimeInSeconds;
    private FuelType fuelType;
    private double emissions;
    private double fuelConsumption;
    private String plate;
}
