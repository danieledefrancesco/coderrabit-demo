package com.tuimm.learningpath.domain.vehicles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class CarTest extends EnginePoweredVehicleTest {
    @Test
    void acceptVisitor_shouldInvokeVisitor() {
        VehicleVisitor<Object> visitor = mock(VehicleVisitor.class);
        Object expectedResult = new Object();
        Car car = createVehicle(UUID.randomUUID(),
                "model",
                1,
                2,
                3,
                4,
                5,
                GenericPlate.from("AA000BB"),
                FuelType.PETROL,
                6,
                7);
        when(visitor.visit(car)).thenReturn(expectedResult);

        Object actualResult = car.acceptVisitor(visitor);

        Assertions.assertEquals(expectedResult, actualResult);
        verify(visitor, times(1)).visit(car);
    }
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
    protected Car createVehicle(UUID id, String model, int maxPeople, double dailyRentPrice, double averageSpeed, double autonomy, int stopTimeInSeconds, Plate plate, FuelType fuelType, double emissions, double fuelConsumption) {
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
