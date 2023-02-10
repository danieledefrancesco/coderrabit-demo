package com.tuimm.learningpath.domain.vehicles;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@RequiredArgsConstructor
@Component
public class VehiclesServiceImpl implements VehiclesService {
    @NonNull
    private final Garage garage;
    @NonNull
    private final VehicleFactory factory;

    @Override
    public Collection<Vehicle> getAllVehicles() {
        return garage.getAllVehicles();
    }

    @Override
    public Vehicle addBike(String model,
                           int maxPeople,
                           double dailyRentPrice,
                           double averageSpeed,
                           double autonomy) {
        Vehicle vehicle = factory.createBike(model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy);
        garage.addVehicle(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle addCar(String model,
                          int maxPeople,
                          double dailyRentPrice,
                          double averageSpeed,
                          double autonomy,
                          int stopTimeInSeconds,
                          String plate,
                          FuelType fuelType,
                          double emissions,
                          double fuelConsumption) {
        Vehicle vehicle = factory.createCar(model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                plate,
                fuelType,
                emissions,
                fuelConsumption);
        garage.addVehicle(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle addPullman(String model,
                              int maxPeople,
                              double dailyRentPrice,
                              double averageSpeed,
                              double autonomy,
                              int stopTimeInSeconds,
                              String plate,
                              FuelType fuelType,
                              double emissions,
                              double fuelConsumption) {
        Vehicle vehicle = factory.createPullman(model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                plate,
                fuelType,
                emissions,
                fuelConsumption);
        garage.addVehicle(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle addScooter(String model,
                              int maxPeople,
                              double dailyRentPrice,
                              double averageSpeed,
                              double autonomy,
                              int stopTimeInSeconds,
                              String plate,
                              FuelType fuelType,
                              double emissions,
                              double fuelConsumption) {
        Vehicle vehicle = factory.createScooter(model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                plate,
                fuelType,
                emissions,
                fuelConsumption);
        garage.addVehicle(vehicle);
        return vehicle;
    }

    @Override
    public void setFuelCost(FuelType fuelType, double cost)
    {
        fuelType.setCost(cost);
    }
}
