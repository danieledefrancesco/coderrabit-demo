package com.tuimm.learningpath.contracts.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuimm.learningpath.domain.vehicles.FuelType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CreateEnginePoweredVehicleRequestDto extends CreateVehicleRequestDto {
    @JsonProperty("stop_time_in_seconds")
    @PositiveOrZero
    private int stopTimeInSeconds;
    @JsonProperty("fuel_type")
    @NotNull
    private FuelType fuelType;
    @PositiveOrZero
    private double emissions;
    @JsonProperty("fuel_consumption")
    @PositiveOrZero
    private double fuelConsumption;
    public abstract String getPlate();
    public abstract void setPlate(String plate);
}
