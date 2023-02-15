package com.tuimm.learningpath.vehicles;

public class CarResponseDtoDtoDto extends EnginePoweredVehicleResponseDtoDto {
    @Override
    public String getType() {
        return "Car";
    }
}
