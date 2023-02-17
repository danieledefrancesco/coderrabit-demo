package com.tuimm.learningpath.trips.dtos;

import com.tuimm.learningpath.drivers.dtos.DriverResponseDto;
import com.tuimm.learningpath.vehicles.dtos.VehicleResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StagePlanResponseDto {
    private LocalDateTime startDateTime;
    private RouteResponseDto route;
    private String destinationWeatherCondition;
    private VehicleResponseDto vehicle;
    private int numberOfPeople;
    private DriverResponseDto driver;
    private double price;
    private double emissions;
    private int requiredStops;
    private LocalDateTime arrivalDateTime;
    private boolean destinationWeatherConditionSuitableForVehicle;
}
