package com.tuimm.learningpath.domain.vehicles;

import java.util.UUID;

class ScooterTest extends EnginePoweredVehicleTest {
    @Override
    protected double getExpectedAverageSpeedReductionFactor() {
        return 0.15;
    }

    @Override
    protected DrivingPolicy getExpectedDrivingPolicy() {

        return DrivingPolicy.builder()
                .suitableForBadWeather(false)
                .minimumDrivingAge(16)
                .drivingProfile(DrivingProfile.CAR_PROFILE)
                .build();
    }

    @Override
    protected ScooterPlate getExpectedPlate() {

        return ScooterPlate.from("AA0000");
    }


    @Override
    protected EnginePoweredVehicle createVehicle(UUID id, String model, int maxPeople, double dailyRentPrice, double averageSpeed, double autonomy, int stopTimeInSeconds, Plate plate, FuelType fuelType, double emissions, double fuelConsumption) {
        return Scooter.builder()
                .fuelConsumption(fuelConsumption)
                .emissions(emissions)
                .fuelType(fuelType)
                .stopTimeInSeconds(stopTimeInSeconds)
                .plate((ScooterPlate) plate)
                .id(id)
                .dailyRentPrice(dailyRentPrice)
                .model(model)
                .maxPeople(maxPeople)
                .averageSpeed(averageSpeed)
                .autonomy(autonomy)
                .build();
    }
}
