package com.tuimm.learningpath.infrastructure.vehicles.dal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class VehicleEntity {
    @Id
    private UUID id;
    private String model;
    private int maxPeople;
    private double dailyRentPrice;
    private double averageSpeed;
    private double autonomy;
    private int stopTimeInSeconds;
    private String plate;
    private String fuelType;
    private double emissions;
    private double fuelConsumption;
    private VehicleType type;
    public enum VehicleType {
        BIKE, CAR, PULLMAN, SCOOTER
    }
}
