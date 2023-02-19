package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.vehicles.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.mockito.Mockito.*;

class CreateCarCommandTest {
    private VehicleFactory vehicleFactory;
    private Garage garage;
    private CreateCarCommand createCarCommand;

    @BeforeEach
    void setUp() {
        vehicleFactory = mock(VehicleFactory.class);
        garage = mock(Garage.class);
        createCarCommand = new CreateCarCommand(garage, vehicleFactory);
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

        CreateCarRequest request = new CreateCarRequest();
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

        Car car = mock(Car.class);
        CarBuilderTestImpl builder = spy(new CarBuilderTestImpl());

        when(vehicleFactory.createCar(any())).then((invocation) -> {
            invocation.getArgument(0, Consumer.class).accept(builder);
            return car;
        });

        Assertions.assertEquals(car, createCarCommand.handle(request));

        verify(vehicleFactory).createCar(any());
        verify(garage).addVehicle(car);

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

    private static class CarBuilderTestImpl extends Car.CarBuilder<Car, CarBuilderTestImpl> {
        @Override
        public Car build() {
            return null;
        }

        @Override
        protected CarBuilderTestImpl self() {
            return this;
        }
    }
}
