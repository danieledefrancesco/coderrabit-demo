package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.vehicles.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.mockito.Mockito.*;

class CreateScooterCommandTest {
    private VehicleFactory vehicleFactory;
    private CreateScooterCommand createScooterCommand;

    @BeforeEach
    void setUp() {
        vehicleFactory = mock(VehicleFactory.class);
        createScooterCommand = new CreateScooterCommand(vehicleFactory);
    }

    @Test
    void handle_shouldReturnExpectedResult() {
        String model = "model";
        int maxNumberOfPeople = 2;
        double dailyRentPrice = 3;
        double averageSpeed = 4;
        double autonomy = 5;
        int stopTimeInSeconds = 6;
        String plate = "AA0000";
        FuelType fuelType = FuelType.PETROL;
        double emissions = 7;
        double fuelConsumption = 8;

        CreateScooterRequest request = new CreateScooterRequest();
        request.setModel(model);
        request.setMaxPeople(maxNumberOfPeople);
        request.setDailyRentPrice(dailyRentPrice);
        request.setAverageSpeed(averageSpeed);
        request.setAutonomy(autonomy);
        request.setStopTimeInSeconds(stopTimeInSeconds);
        request.setPlate(plate);
        request.setFuelType(fuelType);
        request.setEmissions(emissions);
        request.setFuelConsumption(fuelConsumption);

        Scooter scooter = mock(Scooter.class);
        ScooterBuilderTestImpl builder = spy(new ScooterBuilderTestImpl());

        when(vehicleFactory.createScooter(any())).then((invocation) -> {
            invocation.getArgument(0, Consumer.class).accept(builder);
            return scooter;
        });

        Assertions.assertEquals(scooter, createScooterCommand.handle(request));

        verify(vehicleFactory).createScooter(any());
        verify(scooter).save();

        verify(builder).model(model);
        verify(builder).maxPeople(maxNumberOfPeople);
        verify(builder).dailyRentPrice(dailyRentPrice);
        verify(builder).averageSpeed(averageSpeed);
        verify(builder).autonomy(autonomy);
        verify(builder).stopTimeInSeconds(stopTimeInSeconds);
        verify(builder).plate(ScooterPlate.from(plate));
        verify(builder).fuelType(fuelType);
        verify(builder).emissions(emissions);
        verify(builder).fuelConsumption(fuelConsumption);
    }

    private static class ScooterBuilderTestImpl extends Scooter.ScooterBuilder<Scooter, ScooterBuilderTestImpl> {
        @Override
        public Scooter build() {
            return null;
        }

        @Override
        protected ScooterBuilderTestImpl self() {
            return this;
        }
    }
}
