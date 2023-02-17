package com.tuimm.learningpath.trips.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteResponseDto {
    private String from;
    private String to;
    private double distanceInKilometers;
}
