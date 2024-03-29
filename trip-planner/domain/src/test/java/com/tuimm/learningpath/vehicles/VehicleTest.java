package com.tuimm.learningpath.vehicles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

abstract class VehicleTest {
    @Test
    void getters_shouldReturnExpectedValues() {
        UUID expectedId = UUID.randomUUID();
        String expectedModel = "model";
        int expectedMaxPeople = 1;
        double expectedDailyRentPrice = 2;
        double expectedAverageSpeed = 2;
        double expectedAutonomy = 4;

        Vehicle vehicle = createVehicle(expectedId, expectedModel, expectedMaxPeople, expectedDailyRentPrice, expectedAverageSpeed, expectedAutonomy);

        Assertions.assertEquals(expectedId, vehicle.getId());
        Assertions.assertEquals(expectedModel, vehicle.getModel());
        Assertions.assertEquals(expectedMaxPeople, vehicle.getMaxPeople());
        Assertions.assertEquals(expectedDailyRentPrice, vehicle.getDailyRentPrice(), 0);
        Assertions.assertEquals(expectedAverageSpeed, vehicle.getAverageSpeed(), 0);
        Assertions.assertEquals(expectedAutonomy, vehicle.getAutonomy(), 0);
        Assertions.assertEquals(getExpectedAverageSpeedReductionFactor(), vehicle.getAverageSpeedReductionFactor(), 0);
        Assertions.assertEquals(getExpectedStopTimeInSeconds(), vehicle.getStopTimeInSeconds());
        Assertions.assertEquals(getExpectedDrivingPolicy(), vehicle.getDrivingPolicy());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenIdIsNull() {
        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> createVehicle(null, "model", 1, 1, 10, 11));
        Assertions.assertEquals("id cannot be null", actualException.getMessage());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenModelIsNull() {
        UUID id = UUID.randomUUID();
        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> createVehicle(id, null, 1, 1, 10, 11));
        Assertions.assertEquals("model cannot be null", actualException.getMessage());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenModelIsBlank() {
        UUID id = UUID.randomUUID();
        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> createVehicle(id, " ", 1, 1, 10, 11));
        Assertions.assertEquals("model cannot be blank", actualException.getMessage());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenMaxPeopleIsLowerThanZero() {
        UUID id = UUID.randomUUID();
        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> createVehicle(id, "model", -1, 1, 10, 11));
        Assertions.assertEquals("maxPeople must be greater than 0", actualException.getMessage());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenMaxPeopleIsEqualToZero() {
        UUID id = UUID.randomUUID();
        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> createVehicle(id, "model", 0, 1, 10, 11));
        Assertions.assertEquals("maxPeople must be greater than 0", actualException.getMessage());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenDailyRentPriceIsLowerThanZero() {
        UUID id = UUID.randomUUID();
        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> createVehicle(id, "model", 1, -1, 10, 11));
        Assertions.assertEquals("dailyRentPrice must be greater than or equal to 0.0", actualException.getMessage());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenAverageSpeedIsLowerThanZero() {
        UUID id = UUID.randomUUID();
        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> createVehicle(id, "model", 1, 0, -1, 11));
        Assertions.assertEquals("averageSpeed must be greater than 0.0", actualException.getMessage());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenAverageSpeedIsEqualToZero() {
        UUID id = UUID.randomUUID();
        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> createVehicle(id, "model", 1, 1, 0, 11));
        Assertions.assertEquals("averageSpeed must be greater than 0.0", actualException.getMessage());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenAutonomyIsLowerThanZero() {
        UUID id = UUID.randomUUID();
        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> createVehicle(id, "model", 1, 0, 1, -1));
        Assertions.assertEquals("autonomy must be greater than 0.0", actualException.getMessage());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenAutonomyIsEqualToZero() {
        UUID id = UUID.randomUUID();
        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> createVehicle(id, "model", 1, 0, 1, 0));
        Assertions.assertEquals("autonomy must be greater than 0.0", actualException.getMessage());
    }
    @Test
    void computeAverageSpeedForPassengersAmount_shouldReturnExpectedValue_whenNumberOfPassengersIsAllowed() {
        double averageSpeed = 10;
        int numberOfPeople = 2;
        double expectedReducedAverageSpeed = averageSpeed * (1 - getExpectedAverageSpeedReductionFactor() * (numberOfPeople - 1));
        Vehicle vehicle = createVehicle(UUID.randomUUID(), "model", 2, 0, averageSpeed, 11);

        Assertions.assertEquals(expectedReducedAverageSpeed, vehicle.computeAverageSpeedForPassengersAmount(numberOfPeople));
    }

    @Test
    void computeAverageSpeed_shouldThrowIllegalArgumentException_whenNumberOfPeopleIsGreaterThanTheMaxAllowedPeople()
    {
        int passengersAmount = 2;
        Vehicle vehicle = createVehicle(UUID.randomUUID(), "model", 1, 2, 3, 4);
        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> vehicle.computeAverageSpeedForPassengersAmount(passengersAmount));
        String expectedMessage = String.format(
                "Error while computing the average speed for %d passengers. The vehicle %s allows at most %d people.",
                passengersAmount,
                vehicle.getId(),
                vehicle.getMaxPeople());
        Assertions.assertEquals(expectedMessage,
                actualException.getMessage());
    }
    protected abstract Vehicle createVehicle(UUID id, String model, int maxPeople, double dailyRentPrice, double averageSpeed, double autonomy);

    protected abstract double getExpectedAverageSpeedReductionFactor();

    protected abstract int getExpectedStopTimeInSeconds();

    protected abstract DrivingPolicy getExpectedDrivingPolicy();
}
