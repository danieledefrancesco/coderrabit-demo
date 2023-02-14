package com.tuimm.learningpath.domain.vehicles;

import java.util.UUID;

public class Scooter extends EnginePoweredVehicle {
    private static final DrivingPolicy DRIVING_POLICY = DrivingPolicy.builder()
            .drivingProfile(DrivingProfile.CAR_PROFILE)
            .minimumDrivingAge(16)
            .suitableForBadWeather(false)
            .build();


    public Scooter(UUID id,
                   String model,
                   int maxPeople,
                   double dailyRentPrice,
                   double averageSpeed,
                   double autonomy,
                   int stopTimeInSeconds,
                   ScooterPlate plate,
                   FuelType fuelType,
                   double emissions,
                   double fuelConsumption) {
        super(id,
                model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                plate,
                fuelType,
                emissions,
                fuelConsumption);
    }

    @Override
    protected double getAverageSpeedReductionFactor() {
        return 0.15;
    }

    @Override
    public DrivingPolicy getDrivingPolicy() {
        return Scooter.DRIVING_POLICY;
    }
}
