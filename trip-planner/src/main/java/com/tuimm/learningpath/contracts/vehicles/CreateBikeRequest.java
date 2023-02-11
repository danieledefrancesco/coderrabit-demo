package com.tuimm.learningpath.contracts.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBikeRequest {
    private String model;
    @JsonProperty("max_people")
    private int maxPeople;
    @JsonProperty("daily_rent_price")
    private double dailyRentPrice;
    private double autonomy;
    @JsonProperty("average_speed")
    private double averageSpeed;
}
