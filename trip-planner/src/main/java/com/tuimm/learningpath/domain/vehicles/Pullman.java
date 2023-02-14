package com.tuimm.learningpath.domain.vehicles;

import java.util.UUID;

public class Pullman extends EnginePoweredVehicle {
    private static final DrivingPolicy DRIVING_POLICY = DrivingPolicy.builder()
            .suitableForBadWeather(true)
            .minimumDrivingAge(21)
            .drivingProfile(DrivingProfile.HGV_PROFILE)
            .build();
    public Pullman(UUID id,
                   String model,
                   int maxPeople,
                   double dailyRentPrice,
                   double averageSpeed,
                   double autonomy,
                   int stopTimeInSeconds,
                   GenericPlate plate,
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
        return 0.03;
    }

    @Override
    public DrivingPolicy getDrivingPolicy() {
        return Pullman.DRIVING_POLICY;
    }
}
