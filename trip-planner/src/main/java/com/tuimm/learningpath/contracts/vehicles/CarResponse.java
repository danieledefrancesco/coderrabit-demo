package com.tuimm.learningpath.contracts.vehicles;

public class CarResponse extends EnginePoweredVehicleResponse {
    @Override
    public String getType() {
        return "Car";
    }
}
