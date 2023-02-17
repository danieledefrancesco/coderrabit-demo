package com.tuimm.learningpath.trips.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuimm.learningpath.drivers.dtos.DriverResponseDto;
import com.tuimm.learningpath.vehicles.dtos.VehicleResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StagePlanResponseDto {
    @JsonProperty("start_date_time")
    private LocalDateTime startDateTime;
    private RouteResponseDto route;
    @JsonProperty("destination_weather_condition")
    private String destinationWeatherCondition;
    private VehicleResponseDto vehicle;
    @JsonProperty("number_of_people")
    private int numberOfPeople;
    private DriverResponseDto driver;
    private double price;
    private double emissions;
    @JsonProperty("required_stops")
    private int requiredStops;
    @JsonProperty("arrival_date_time")
    private LocalDateTime arrivalDateTime;
    @JsonProperty("vehicle_not_suitable_for_destination_weather_condition")
    private boolean vehicleNotSuitableForDestinationWeatherCondition;
}
