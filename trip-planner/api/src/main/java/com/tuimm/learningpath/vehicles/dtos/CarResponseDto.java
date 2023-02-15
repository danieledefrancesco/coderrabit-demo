package com.tuimm.learningpath.vehicles.dtos;

public class CarResponseDto extends EnginePoweredVehicleResponseDto {
    @Override
    public String getType() {
        return "Car";
    }
}
