package com.tuimm.learningpath.vehicles.dtos;

public class BikeResponseDto extends VehicleResponseDto {
    @Override
    public String getType() {
        return "Bike";
    }
}
