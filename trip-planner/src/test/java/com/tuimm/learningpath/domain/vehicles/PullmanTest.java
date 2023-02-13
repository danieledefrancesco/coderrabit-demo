package com.tuimm.learningpath.domain.vehicles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class PullmanTest extends EnginePoweredVehicleTest {
    @Test
    void acceptVisitor_shouldInvokeVisitor() {
        VehicleVisitor<Object> visitor = mock(VehicleVisitor.class);
        Object expectedResult = new Object();
        Pullman pullman = createVehicle(UUID.randomUUID(),
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
        when(visitor.visit(pullman)).thenReturn(expectedResult);

        Object actualResult = pullman.acceptVisitor(visitor);

        Assertions.assertEquals(expectedResult, actualResult);
        verify(visitor, times(1)).visit(pullman);
    }

    @Override
    protected double getExpectedAverageSpeedReductionFactor() {
        return 0.03;
    }

    @Override
    protected DrivingProfile getExpectedDrivingProfile() {
        return DrivingProfile.HGV_PROFILE;
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
    protected Pullman createVehicle(UUID id, String model, int maxPeople, double dailyRentPrice, double averageSpeed, double autonomy, int stopTimeInSeconds, Plate plate, FuelType fuelType, double emissions, double fuelConsumption) {
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
