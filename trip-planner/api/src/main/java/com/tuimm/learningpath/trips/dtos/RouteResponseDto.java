package com.tuimm.learningpath.trips.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteResponseDto {
    private String from;
    private String to;
    @JsonProperty("distance_in_kilometers")
    private double distanceInKilometers;
}
