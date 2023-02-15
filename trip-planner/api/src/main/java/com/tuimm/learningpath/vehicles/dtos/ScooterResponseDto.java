package com.tuimm.learningpath.vehicles.dtos;

public class ScooterResponseDto extends EnginePoweredVehicleResponseDto {
    @Override
    public String getType() {
        return "Scooter";
    }
}
