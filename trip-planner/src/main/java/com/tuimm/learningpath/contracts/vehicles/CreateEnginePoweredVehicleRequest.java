package com.tuimm.learningpath.contracts.vehicles;

import com.tuimm.learningpath.domain.vehicles.FuelType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CreateEnginePoweredVehicleRequest extends CreateVehicleRequest {
    private int stopTimeInSeconds;
    private String plate;
    private FuelType fuelType;
    private double emissions;
    private double fuelConsumption;
}
