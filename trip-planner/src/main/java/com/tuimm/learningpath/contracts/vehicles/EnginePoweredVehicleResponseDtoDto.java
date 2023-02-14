package com.tuimm.learningpath.contracts.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class EnginePoweredVehicleResponseDtoDto extends VehicleResponseDto {
    private String plate;
    @JsonProperty("fuel_type")
    private String fuelType;
    private double emissions;
    @JsonProperty("fuel_consumption")
    private double fuelConsumption;
}
