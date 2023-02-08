package com.tuimm.leaarningpath.domain.vehicles;

import java.util.UUID;

public class Car extends EnginePoweredVehicle {
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
    public boolean hasCoverage() {
        return true;
    }

    @Override
    public DrivingProfile getDrivingProfile() {
        return DrivingProfile.CAR_PROFILE;
    }
}
