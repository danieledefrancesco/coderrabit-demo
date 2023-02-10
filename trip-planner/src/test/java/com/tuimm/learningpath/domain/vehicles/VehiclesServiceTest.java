package com.tuimm.learningpath.domain.vehicles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.UUID;

import static org.mockito.Mockito.*;

class VehiclesServiceTest {
    private VehiclesService vehiclesService;
    private Garage garage;
    private VehicleFactory vehicleFactory;

    @BeforeEach
    public void setUp() {
        garage = mock(Garage.class);
        vehicleFactory = mock(VehicleFactory.class);
        vehiclesService = new VehiclesServiceImpl(garage, vehicleFactory);
    }

    @Test
    void addBike_shouldCreateANewBikeAndAddItToTheGarage() {
        String model = "model";
        int maxPeople = 1;
        double dailyRentPrice = 2;
        double averageSpeed = 3;
        double autonomy = 4;

        Bike bike = new Bike(UUID.randomUUID(),
                model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy);

        when(vehicleFactory.createBike(model, maxPeople, dailyRentPrice, averageSpeed, autonomy))
                .thenReturn(bike);

        doNothing().when(garage).addVehicle(bike);

        Vehicle newBike = vehiclesService.addBike(model, maxPeople, dailyRentPrice, averageSpeed, autonomy);

        Assertions.assertEquals(bike, newBike);
        verify(vehicleFactory, times(1)).createBike(model, maxPeople, dailyRentPrice, averageSpeed, autonomy);
        verify(garage, times(1)).addVehicle(bike);
    }

    @Test
    void addCar_shouldCreateANewCarAndAddItToTheGarage() {
        String model = "model";
        int maxPeople = 1;
        double dailyRentPrice = 2;
        double averageSpeed = 3;
        double autonomy = 4;
        int stopTimeInSeconds = 5;
        String plate = "AA000BB";
        FuelType fuelType = FuelType.PETROL;
        double emissions = 6;
        double fuelConsumption = 7;

        Car car = new Car(UUID.randomUUID(),
                model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                GenericPlate.from(plate),
                fuelType,
                emissions,
                fuelConsumption);

        when(vehicleFactory.createCar(model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                plate,
                fuelType,
                emissions,
                fuelConsumption))
                .thenReturn(car);

        doNothing().when(garage).addVehicle(car);

        Vehicle newCar = vehiclesService.addCar(model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                plate,
                fuelType,
                emissions,
                fuelConsumption);

        Assertions.assertEquals(car, newCar);
        verify(vehicleFactory, times(1)).createCar(model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                plate,
                fuelType,
                emissions,
                fuelConsumption);
        verify(garage, times(1)).addVehicle(car);
    }

    @Test
    void addPullman_shouldCreateANewPullmanAndAddItToTheGarage() {
        String model = "model";
        int maxPeople = 1;
        double dailyRentPrice = 2;
        double averageSpeed = 3;
        double autonomy = 4;
        int stopTimeInSeconds = 5;
        String plate = "AA000BB";
        FuelType fuelType = FuelType.PETROL;
        double emissions = 6;
        double fuelConsumption = 7;

        Pullman pullman = new Pullman(UUID.randomUUID(),
                model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                GenericPlate.from(plate),
                fuelType,
                emissions,
                fuelConsumption);

        when(vehicleFactory.createPullman(model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                plate,
                fuelType,
                emissions,
                fuelConsumption))
                .thenReturn(pullman);

        doNothing().when(garage).addVehicle(pullman);

        Vehicle newPullman = vehiclesService.addPullman(model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                plate,
                fuelType,
                emissions,
                fuelConsumption);

        Assertions.assertEquals(pullman, newPullman);
        verify(vehicleFactory, times(1)).createPullman(model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                plate,
                fuelType,
                emissions,
                fuelConsumption);
        verify(garage, times(1)).addVehicle(pullman);
    }

    @Test
    void addScooter_shouldCreateANewScooterAndAddItToTheGarage() {
        String model = "model";
        int maxPeople = 1;
        double dailyRentPrice = 2;
        double averageSpeed = 3;
        double autonomy = 4;
        int stopTimeInSeconds = 5;
        String plate = "AA0000";
        FuelType fuelType = FuelType.PETROL;
        double emissions = 6;
        double fuelConsumption = 7;

        Scooter scooter = new Scooter(UUID.randomUUID(),
                model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                ScooterPlate.from(plate),
                fuelType,
                emissions,
                fuelConsumption);

        when(vehicleFactory.createScooter(model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                plate,
                fuelType,
                emissions,
                fuelConsumption))
                .thenReturn(scooter);

        doNothing().when(garage).addVehicle(scooter);

        Vehicle newScooter = vehiclesService.addScooter(model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                plate,
                fuelType,
                emissions,
                fuelConsumption);

        Assertions.assertEquals(scooter, newScooter);
        verify(vehicleFactory, times(1)).createScooter(model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                plate,
                fuelType,
                emissions,
                fuelConsumption);
        verify(garage, times(1)).addVehicle(scooter);
    }
    @Test
    void getAllVehicles_shouldReturnAllTheVehiclesOfTheGarage() {
        Collection<Vehicle> vehicles = (Collection<Vehicle>) mock(Collection.class);
        when(garage.getAllVehicles()).thenReturn(vehicles);
        Assertions.assertEquals(vehicles, vehiclesService.getAllVehicles());
        verify(garage, times(1)).getAllVehicles();
    }
    @Test
    void setFuelCost_shouldSetTheFuelCost() {
        double newCost = 1.912345678;
        vehiclesService.setFuelCost(FuelType.PETROL, newCost);
        Assertions.assertEquals(newCost, FuelType.PETROL.getCost(), 0);
    }
}
