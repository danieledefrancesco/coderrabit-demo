package com.tuimm.learningpath.trips.dal;

import com.tuimm.learningpath.drivers.dal.DriverEntity;
import com.tuimm.learningpath.vehicles.dal.VehicleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@Table(name = "stage_plans")
public class StagePlanEntity {
    @Column(name = "trip_id")
    private UUID tripId;
    private LocalDateTime startDateTime;
    private String fromName;
    private double fromLatitude;
    private double fromLongitude;
    private String toName;
    private double toLatitude;
    private double toLongitude;
    private double distanceInKilometers;
    private String drivingProfile;
    private String destinationWeatherCondition;
    @ManyToOne(fetch = FetchType.EAGER)
    private VehicleEntity vehicle;
    private int numberOfPeople;
    @ManyToOne(fetch = FetchType.EAGER)
    private DriverEntity driver;
}
