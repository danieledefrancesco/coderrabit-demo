package com.tuimm.learningpath.vehicles;

public class ScooterResponseDtoDto extends EnginePoweredVehicleResponseDtoDto {
    @Override
    public String getType() {
        return "Scooter";
    }
}
