package com.tuimm.learningpath.contracts.vehicles;

public class BikeResponseDtoDto extends VehicleResponseDto {
    @Override
    public String getType() {
        return "Bike";
    }
}
