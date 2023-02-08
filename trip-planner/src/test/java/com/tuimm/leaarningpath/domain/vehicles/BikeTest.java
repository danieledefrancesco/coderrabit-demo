package com.tuimm.leaarningpath.domain.vehicles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class BikeTest extends AbstractVehicleTest {

    @Test
    void getEmissions_shouldReturnZero() {
        Bike bike = createVehicle(UUID.randomUUID(), "model", 1, 2, 3, 4);
        Assertions.assertEquals(0, bike.getEmissions(), 0);
    }

    @Test
    void computePrice_shouldReturnExpectedValue() {
        double dailyRentPrice = 10.5;
        int numberOfDays = 2;
        double expectedPrice = 21;
        Bike bike = createVehicle(UUID.randomUUID(), "model", 1, dailyRentPrice, 3, 4);
        Assertions.assertEquals(expectedPrice, bike.computePrice(numberOfDays, 100000), 0);
    }

    @Override
    protected Bike createVehicle(UUID id, String model, int maxPeople, double dailyRentPrice, double averageSpeed, double autonomy) {
        return new Bike(id, model, maxPeople, dailyRentPrice, averageSpeed, autonomy);
    }

    @Override
    protected double getExpectedAverageSpeedReductionFactor() {
        return 0.3;
    }

    @Override
    protected int getExpectedStopTimeInSeconds() {
        return 8 * 60 * 60;
    }

    @Override
    protected DrivingProfile getExpectedDrivingProfile() {
        return DrivingProfile.BIKE_PROFILE;
    }
}
