package com.tuimm.learningpath.domain.vehicles;

import java.util.UUID;

class PullmanTest extends EnginePoweredVehicleTest {
    @Override
    protected double getExpectedAverageSpeedReductionFactor() {
        return 0.03;
    }

    @Override
    protected DrivingPolicy getExpectedDrivingPolicy() {

        return DrivingPolicy.builder()
                .suitableForBadWeather(true)
                .minimumDrivingAge(21)
                .drivingProfile(DrivingProfile.HGV_PROFILE)
                .build();
    }

    @Override
    protected GenericPlate getExpectedPlate() {

        return GenericPlate.from("AA000BB");
    }

    @Override
    protected EnginePoweredVehicle createVehicle(UUID id, String model, int maxPeople, double dailyRentPrice, double averageSpeed, double autonomy, int stopTimeInSeconds, Plate plate, FuelType fuelType, double emissions, double fuelConsumption) {
        return new Pullman(id,
                model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                (GenericPlate) plate,
                fuelType,
                emissions,
                fuelConsumption);
    }
}
