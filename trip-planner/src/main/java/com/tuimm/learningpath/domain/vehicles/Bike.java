package com.tuimm.learningpath.domain.vehicles;

import java.util.UUID;

public class Bike extends Vehicle {
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
    public <T> T acceptVisitor(VehicleVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean hasCoverage() {
        return false;
    }

    @Override
    public DrivingProfile getDrivingProfile() {

        return DrivingProfile.BIKE_PROFILE;
    }
}
