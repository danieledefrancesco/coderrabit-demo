package com.tuimm.learningpath.trips.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TripResponseDto {
    private UUID id;
    private TripPlanResponseDto plan;
}
