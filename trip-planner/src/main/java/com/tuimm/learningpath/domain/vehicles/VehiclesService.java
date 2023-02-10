package com.tuimm.learningpath.domain.vehicles;

import java.util.Collection;

public interface VehiclesService {
    Collection<Vehicle> getAllVehicles();

    Vehicle addBike(String model,
                    int maxPeople,
                    double dailyRentPrice,
                    double averageSpeed,
                    double autonomy);

    Vehicle addCar(String model,
                   int maxPeople,
                   double dailyRentPrice,
                   double averageSpeed,
                   double autonomy,
                   int stopTimeInSeconds,
                   String plate,
                   FuelType fuelType,
                   double emissions,
                   double fuelConsumption);

    Vehicle addPullman(String model,
                       int maxPeople,
                       double dailyRentPrice,
                       double averageSpeed,
                       double autonomy,
                       int stopTimeInSeconds,
                       String plate,
                       FuelType fuelType,
                       double emissions,
                       double fuelConsumption);

    Vehicle addScooter(String model,
                       int maxPeople,
                       double dailyRentPrice,
                       double averageSpeed,
                       double autonomy,
                       int stopTimeInSeconds,
                       String plate,
                       FuelType fuelType,
                       double emissions,
                       double fuelConsumption);

    void setFuelCost(FuelType fuelType, double cost);
}
