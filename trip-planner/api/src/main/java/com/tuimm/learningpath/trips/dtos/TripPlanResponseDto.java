package com.tuimm.learningpath.trips.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
public class TripPlanResponseDto {
    private Collection<StagePlanResponseDto> stages;
    private LocalDateTime arrivalDateTime;
    private double totalPrice;
    private double totalEmissions;
}
