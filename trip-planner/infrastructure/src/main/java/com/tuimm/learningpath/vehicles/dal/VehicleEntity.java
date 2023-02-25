package com.tuimm.learningpath.vehicles.dal;

import com.tuimm.learningpath.drivers.dal.SlotEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "vehicles")
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
    @ElementCollection(targetClass = com.tuimm.learningpath.drivers.dal.SlotEntity.class, fetch = FetchType.EAGER)
    private Set<SlotEntity> reservedTimeSlots;
}
