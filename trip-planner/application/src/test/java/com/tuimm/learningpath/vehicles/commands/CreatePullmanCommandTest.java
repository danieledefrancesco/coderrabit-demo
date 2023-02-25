package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.vehicles.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.mockito.Mockito.*;

class CreatePullmanCommandTest {
    private VehicleFactory vehicleFactory;
    private CreatePullmanCommand createPullmanCommand;

    @BeforeEach
    void setUp() {
        vehicleFactory = mock(VehicleFactory.class);
        createPullmanCommand = new CreatePullmanCommand(vehicleFactory);
    }

    @Test
    void handle_shouldReturnExpectedResult() {
        String model = "model";
        int maxNumberOfPeople = 2;
        double dailyRentPrice = 3;
        double averageSpeed = 4;
        double autonomy = 5;
        int stopTimeInSeconds = 6;
        String plate = "AA000BB";
        FuelType fuelType = FuelType.PETROL;
        double emissions = 7;
        double fuelConsumption = 8;

        CreatePullmanRequest request = new CreatePullmanRequest();
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

        Pullman pullman = mock(Pullman.class);
        PullmanBuilderTestImpl builder = spy(new PullmanBuilderTestImpl());

        when(vehicleFactory.createPullman(any())).then((invocation) -> {
            invocation.getArgument(0, Consumer.class).accept(builder);
            return pullman;
        });

        Assertions.assertEquals(pullman, createPullmanCommand.handle(request));

        verify(vehicleFactory).createPullman(any());
        verify(pullman).save();

        verify(builder).model(model);
        verify(builder).maxPeople(maxNumberOfPeople);
        verify(builder).dailyRentPrice(dailyRentPrice);
        verify(builder).averageSpeed(averageSpeed);
        verify(builder).autonomy(autonomy);
        verify(builder).stopTimeInSeconds(stopTimeInSeconds);
        verify(builder).plate(GenericPlate.from(plate));
        verify(builder).fuelType(fuelType);
        verify(builder).emissions(emissions);
        verify(builder).fuelConsumption(fuelConsumption);
    }

    private static class PullmanBuilderTestImpl extends Pullman.PullmanBuilder<Pullman, PullmanBuilderTestImpl> {
        @Override
        public Pullman build() {
            return null;
        }

        @Override
        protected PullmanBuilderTestImpl self() {
            return this;
        }
    }
}
