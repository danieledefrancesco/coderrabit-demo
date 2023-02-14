package com.tuimm.learningpath.contracts.vehicles;

public class CarResponseDtoDtoDto extends EnginePoweredVehicleResponseDtoDto {
    @Override
    public String getType() {
        return "Car";
    }
}
