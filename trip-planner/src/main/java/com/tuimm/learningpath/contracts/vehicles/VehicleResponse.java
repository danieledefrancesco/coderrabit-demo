package com.tuimm.learningpath.contracts.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class VehicleResponse {
    private UUID id;
    private String model;
    @JsonProperty("max_people")
    private int maxPeople;
    @JsonProperty("average_speed")
    private double averageSpeed;
    @JsonProperty("daily_rent_price")
    private double dailyRentPrice;
    @JsonProperty("stop_time_in_seconds")
    private int stopTimeInSeconds;
    public abstract String getType();

}
