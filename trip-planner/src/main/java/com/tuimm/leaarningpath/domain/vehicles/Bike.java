package com.tuimm.leaarningpath.domain.vehicles;

import java.util.UUID;

public class Bike extends AbstractVehicle{
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
    public DrivingProfile getDrivingProfile() {
        return DrivingProfile.BIKE_PROFILE;
    }
}
