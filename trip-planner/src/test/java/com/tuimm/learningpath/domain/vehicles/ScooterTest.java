package com.tuimm.learningpath.domain.vehicles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ScooterTest extends EnginePoweredVehicleTest {
    @Test
    void acceptVisitor_shouldInvokeVisitor() {
        VehicleVisitor<Object> visitor = mock(VehicleVisitor.class);
        Object expectedResult = new Object();
        Scooter scooter = createVehicle(UUID.randomUUID(),
                "model",
                1,
                2,
                3,
                4,
                5,
                ScooterPlate.from("AA0000"),
                FuelType.PETROL,
                6,
                7);
        when(visitor.visit(scooter)).thenReturn(expectedResult);

        Object actualResult = scooter.acceptVisitor(visitor);

        Assertions.assertEquals(expectedResult, actualResult);
        verify(visitor, times(1)).visit(scooter);
    }
    @Override
    protected double getExpectedAverageSpeedReductionFactor() {
        return 0.15;
    }

    @Override
    protected DrivingProfile getExpectedDrivingProfile() {
        return DrivingProfile.CAR_PROFILE;
    }

    @Override
    protected boolean getExpectedHasCoverage() {
        return false;
    }

    @Override
    protected ScooterPlate getExpectedPlate() {

        return ScooterPlate.from("AA0000");
    }


    @Override
    protected Scooter createVehicle(UUID id, String model, int maxPeople, double dailyRentPrice, double averageSpeed, double autonomy, int stopTimeInSeconds, Plate plate, FuelType fuelType, double emissions, double fuelConsumption) {
        return new Scooter(id,
                model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                (ScooterPlate) plate,
                fuelType,
                emissions,
                fuelConsumption);
    }
}
