package com.tuimm.learningpath.vehicles.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class EnginePoweredVehicleResponseDto extends VehicleResponseDto {
    private String plate;
    @JsonProperty("fuel_type")
    private String fuelType;
    private double emissions;
    @JsonProperty("fuel_consumption")
    private double fuelConsumption;
}
