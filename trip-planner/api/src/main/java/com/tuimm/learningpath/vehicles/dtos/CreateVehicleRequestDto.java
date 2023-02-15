package com.tuimm.learningpath.vehicles.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CreateVehicleRequestDto {
    @NotBlank
    @NotEmpty
    @NotNull
    private String model;
    @JsonProperty("max_people")
    @Positive
    private int maxPeople;
    @JsonProperty("daily_rent_price")
    @PositiveOrZero
    private double dailyRentPrice;
    @Positive
    private double autonomy;
    @JsonProperty("average_speed")
    @Positive
    private double averageSpeed;
}
