package com.tuimm.learningpath.vehicles.dtos;

public class PullmanResponseDto extends EnginePoweredVehicleResponseDto {
    @Override
    public String getType() {
        return "Pullman";
    }
}
