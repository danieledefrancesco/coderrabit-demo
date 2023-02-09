package com.tuimm.leaarningpath.domain.vehicles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

abstract class EnginePoweredVehicleTest extends AbstractVehicleTest {
    @Test
    void enginePoweredVehicleGetters_shouldReturnExpectedValues() {
        Plate expectedPlate = getExpectedPlate();
        FuelType expectedFuelType = FuelType.PETROL;
        double expectedFuelConsumption = 2.5;

        EnginePoweredVehicle vehicle = createVehicle(UUID.randomUUID(),
                "model",
                1,
                2,
                3,
                4,
                5,
                expectedPlate,
                expectedFuelType,
                6,
                expectedFuelConsumption);

        Assertions.assertEquals(expectedPlate, vehicle.getPlate());
        Assertions.assertEquals(expectedFuelType, vehicle.getFuelType());
        Assertions.assertEquals(expectedFuelConsumption, vehicle.getFuelConsumption(), 0);
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenStopTimeInSecondsIsLowerThanZero() {
        UUID id = UUID.randomUUID();
        Plate plate = getExpectedPlate();
        Exception actualException = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> createVehicle(id,
                        "model",
                        1,
                        2,
                        3,
                        4,
                        -1,
                        plate,
                        FuelType.PETROL,
                        6,
                        7));
        Assertions.assertEquals(
                "stopTimeInSeconds must be greater than or equal to 0",
                actualException.getMessage());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenPlateIsNull() {
        UUID id = UUID.randomUUID();
        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> createVehicle(id,
                        "model",
                        1,
                        2,
                        3,
                        4,
                        0,
                        null,
                        FuelType.PETROL,
                        6,
                        7));
        Assertions.assertEquals("plate cannot be null", actualException.getMessage());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenFuelTypeIsNull() {
        UUID id = UUID.randomUUID();
        Plate plate = getExpectedPlate();
        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> createVehicle(id,
                        "model",
                        1,
                        2,
                        3,
                        4,
                        0,
                        plate,
                        null,
                        0,
                        0));
        Assertions.assertEquals("fuelType cannot be null", actualException.getMessage());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenEmissionsIsLowerThanZero() {
        UUID id = UUID.randomUUID();
        Plate plate = getExpectedPlate();
        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> createVehicle(id,
                        "model",
                        1,
                        2,
                        3,
                        4,
                        0,
                        plate,
                        FuelType.PETROL,
                        -1,
                        0));
        Assertions.assertEquals("emissions must be greater than or equal to 0", actualException.getMessage());
    }

    @Test
    void constructor_shouldThrowIllegalArgumentException_whenFuelConsumptionIsLowerThanZero() {
        UUID id = UUID.randomUUID();
        Plate plate = getExpectedPlate();
        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> createVehicle(id,
                        "model",
                        1,
                        2,
                        3,
                        4,
                        0,
                        plate,
                        FuelType.PETROL,
                        0,
                        -1));
        Assertions.assertEquals("fuelConsumption must be greater than or equal to 0", actualException.getMessage());
    }

    @Test
    void computePrice_shouldReturnExpectedValue() {
        double dailyRentPrice = 10.5;
        int numberOfDays = 2;
        double fuelCost = 1.5;
        double fuelConsumption = 10;
        int kilometers = 500;
        double expectedRentPrice = 21;
        double expectedFuelPrice = 75;
        double expectedPrice = expectedRentPrice + expectedFuelPrice;

        FuelType.PETROL.setCost(fuelCost);

        EnginePoweredVehicle vehicle = createVehicle(UUID.randomUUID(),
                "model",
                1,
                dailyRentPrice,
                3,
                4,
                0,
                getExpectedPlate(),
                FuelType.PETROL,
                0,
                fuelConsumption);
        Assertions.assertEquals(expectedPrice, vehicle.computePrice(numberOfDays, kilometers), 0);
    }

    @Override
    protected String getExpectedToString(AbstractVehicle vehicle) {
        EnginePoweredVehicle enginePoweredVehicle = (EnginePoweredVehicle) vehicle;
        return super.getExpectedToString(enginePoweredVehicle) +
                System.lineSeparator() +
                String.format("  plate: %s%s", enginePoweredVehicle.getPlate(), System.lineSeparator()) +
                String.format("  fuelType: %s%s", enginePoweredVehicle.getFuelType(), System.lineSeparator()) +
                String.format("  fuelConsumption: %f km/l%s", enginePoweredVehicle.getFuelConsumption(), System.lineSeparator()) +
                String.format("  emissions: %f CO2/km", enginePoweredVehicle.getEmissions());
    }

    @Override
    protected EnginePoweredVehicle createVehicle(UUID id, String model, int maxPeople, double dailyRentPrice, double averageSpeed, double autonomy) {
        return createVehicle(id,
                model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                getExpectedStopTimeInSeconds(),
                getExpectedPlate(),
                FuelType.PETROL,
                1,
                2);
    }

    @Override
    protected int getExpectedStopTimeInSeconds() {
        return 11;
    }

    protected abstract Plate getExpectedPlate();

    protected abstract EnginePoweredVehicle createVehicle(UUID id,
                                                          String model,
                                                          int maxPeople,
                                                          double dailyRentPrice,
                                                          double averageSpeed,
                                                          double autonomy,
                                                          int stopTimeInSeconds,
                                                          Plate plate,
                                                          FuelType fuelType,
                                                          double emissions,
                                                          double fuelConsumption);
}
