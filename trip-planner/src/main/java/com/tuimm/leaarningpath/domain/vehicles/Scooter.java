package com.tuimm.leaarningpath.domain.vehicles;

import java.util.UUID;

public class Scooter extends EnginePoweredVehicle {
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
    public DrivingProfile getDrivingProfile() {
        return DrivingProfile.CAR_PROFILE;
    }
}
