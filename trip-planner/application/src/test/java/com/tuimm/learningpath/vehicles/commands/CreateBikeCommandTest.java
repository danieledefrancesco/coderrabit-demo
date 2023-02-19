package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.vehicles.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.mockito.Mockito.*;

class CreateBikeCommandTest {
    private VehicleFactory vehicleFactory;
    private Garage garage;
    private CreateBikeCommand createBikeCommand;

    @BeforeEach
    void setUp() {
        vehicleFactory = mock(VehicleFactory.class);
        garage = mock(Garage.class);
        createBikeCommand = new CreateBikeCommand(vehicleFactory, garage);
    }

    @Test
    void handle_shouldReturnExpectedResult() {
        String model = "model";
        int maxNumberOfPeople = 2;
        double dailyRentPrice = 3;
        double averageSpeed = 4;
        double autonomy = 5;

        CreateBikeRequest request = new CreateBikeRequest();
        request.setModel(model);
        request.setMaxPeople(maxNumberOfPeople);
        request.setDailyRentPrice(dailyRentPrice);
        request.setAverageSpeed(averageSpeed);
        request.setAutonomy(autonomy);

        Bike bike = mock(Bike.class);
        BikeBuilderTestImpl builder = spy(new BikeBuilderTestImpl());

        when(vehicleFactory.createBike(any())).then((invocation) -> {
            invocation.getArgument(0, Consumer.class).accept(builder);
            return bike;
        });

        Assertions.assertEquals(bike, createBikeCommand.handle(request));

        verify(vehicleFactory).createBike(any());
        verify(garage).addVehicle(bike);

        verify(builder).model(model);
        verify(builder).maxPeople(maxNumberOfPeople);
        verify(builder).dailyRentPrice(dailyRentPrice);
        verify(builder).averageSpeed(averageSpeed);
        verify(builder).autonomy(autonomy);
    }

    private static class BikeBuilderTestImpl extends Bike.BikeBuilder<Bike, BikeBuilderTestImpl> {
        @Override
        public Bike build() {
            return null;
        }

        @Override
        protected BikeBuilderTestImpl self() {
            return this;
        }
    }
}
