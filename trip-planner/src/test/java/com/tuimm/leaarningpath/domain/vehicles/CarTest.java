package com.tuimm.leaarningpath.domain.vehicles;

import java.util.UUID;

class CarTest extends EnginePoweredVehicleTest {
    @Override
    protected double getExpectedAverageSpeedReductionFactor() {
        return 0.1;
    }

    @Override
    protected DrivingProfile getExpectedDrivingProfile() {
        return DrivingProfile.CAR_PROFILE;
    }

    @Override
    protected boolean getExpectedHasCoverage() {
        return true;
    }

    @Override
    protected GenericPlate getExpectedPlate() {
        return GenericPlate.from("AA000BB");
    }


    @Override
    protected EnginePoweredVehicle createVehicle(UUID id, String model, int maxPeople, double dailyRentPrice, double averageSpeed, double autonomy, int stopTimeInSeconds, Plate plate, FuelType fuelType, double emissions, double fuelConsumption) {
        return new Car(id,
                model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                (GenericPlate)plate,
                fuelType,
                emissions,
                fuelConsumption);
    }
}
