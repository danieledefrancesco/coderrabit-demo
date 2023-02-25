package com.tuimm.learningpath.vehicles;

import com.tuimm.learningpath.common.AggregateManager;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.mock;

class CarTest extends EnginePoweredVehicleTest {
    @Override
    protected double getExpectedAverageSpeedReductionFactor() {
        return 0.1;
    }

    @Override
    protected DrivingPolicy getExpectedDrivingPolicy() {

        return DrivingPolicy.builder()
                .suitableForBadWeather(true)
                .minimumDrivingAge(18)
                .drivingProfile(DrivingProfile.CAR_PROFILE)
                .build();
    }

    @Override
    protected GenericPlate getExpectedPlate() {
        return GenericPlate.from("AA000BB");
    }


    @Override
    protected EnginePoweredVehicle createVehicle(UUID id, String model, int maxPeople, double dailyRentPrice, double averageSpeed, double autonomy, int stopTimeInSeconds, Plate plate, FuelType fuelType, double emissions, double fuelConsumption) {
        return Car.builder()
                .fuelConsumption(fuelConsumption)
                .emissions(emissions)
                .fuelType(fuelType)
                .stopTimeInSeconds(stopTimeInSeconds)
                .plate((GenericPlate) plate)
                .id(id)
                .dailyRentPrice(dailyRentPrice)
                .model(model)
                .maxPeople(maxPeople)
                .averageSpeed(averageSpeed)
                .autonomy(autonomy)
                .reservedTimeSlots(Collections.emptyList())
                .aggregateManager(mock(AggregateManager.class))
                .build();
    }
}
