package com.tuimm.learningpath.contracts.vehicles;

public class ScooterResponseDtoDto extends EnginePoweredVehicleResponseDtoDto {
    @Override
    public String getType() {
        return "Scooter";
    }
}
