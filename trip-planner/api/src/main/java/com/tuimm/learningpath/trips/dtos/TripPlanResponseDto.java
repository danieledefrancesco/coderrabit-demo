package com.tuimm.learningpath.trips.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
public class TripPlanResponseDto {
    private Collection<StagePlanResponseDto> stages;
    @JsonProperty("arrival_date_time")
    private LocalDateTime arrivalDateTime;
    @JsonProperty("total_price")
    private double totalPrice;
    @JsonProperty("total_emissions")
    private double totalEmissions;
}
