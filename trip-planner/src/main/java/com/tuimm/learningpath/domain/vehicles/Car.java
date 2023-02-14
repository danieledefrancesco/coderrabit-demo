package com.tuimm.learningpath.domain.vehicles;

import java.util.UUID;
public class Car extends EnginePoweredVehicle {
    private static final DrivingPolicy DRIVING_POLICY = DrivingPolicy.builder()
            .drivingProfile(DrivingProfile.CAR_PROFILE)
            .minimumDrivingAge(18)
            .suitableForBadWeather(true)
            .build();

    public Car(UUID id,
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
        return 0.1;
    }

    @Override
    public DrivingPolicy getDrivingPolicy() {
        return Car.DRIVING_POLICY;
    }
}
