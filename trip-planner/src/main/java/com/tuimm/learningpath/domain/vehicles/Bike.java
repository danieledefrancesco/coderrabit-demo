package com.tuimm.learningpath.domain.vehicles;

import java.util.UUID;

public class Bike extends Vehicle {
    private static final DrivingPolicy DRIVING_POLICY = DrivingPolicy.builder()
            .suitableForBadWeather(false)
            .minimumDrivingAge(0)
            .drivingProfile(DrivingProfile.BIKE_PROFILE)
            .build();

    public Bike(UUID id,
                String model,
                int maxPeople,
                double dailyRentPrice,
                double averageSpeed,
                double autonomy) {
        super(id, model, maxPeople, dailyRentPrice, averageSpeed, autonomy);
    }

    @Override
    double getAverageSpeedReductionFactor() {
        return 0.3;
    }

    @Override
    public int getStopTimeInSeconds() {
        return 8 * 60 * 60;
    }

    @Override
    public double getEmissions() {
        return 0;
    }

    @Override
    public DrivingPolicy getDrivingPolicy() {
        return Bike.DRIVING_POLICY;
    }
}
