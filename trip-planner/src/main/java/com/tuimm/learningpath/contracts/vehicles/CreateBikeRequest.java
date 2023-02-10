package com.tuimm.learningpath.contracts.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateBikeRequest {
    private final String model;
    @JsonProperty("max_people")
    private final int maxPeople;
    @JsonProperty("daily_rent_price")
    private final double dailyRentPrice;
    private final double autonomy;
    @JsonProperty("average_speed")
    private final double averageSpeed;
}
