package com.tuimm.learningpath.domain.vehicles;

public interface VehicleFactory {
    Bike createBike(String model,
                    int maxPeople,
                    double dailyRentPrice,
                    double averageSpeed,
                    double autonomy);

    Car createCar(String model,
                  int maxPeople,
                  double dailyRentPrice,
                  double averageSpeed,
                  double autonomy,
                  int stopTimeInSeconds,
                  String plate,
                  FuelType fuelType,
                  double emissions,
                  double fuelConsumption);

    Pullman createPullman(String model,
                          int maxPeople,
                          double dailyRentPrice,
                          double averageSpeed,
                          double autonomy,
                          int stopTimeInSeconds,
                          String plate,
                          FuelType fuelType,
                          double emissions,
                          double fuelConsumption);

    Scooter createScooter(String model,
                          int maxPeople,
                          double dailyRentPrice,
                          double averageSpeed,
                          double autonomy,
                          int stopTimeInSeconds,
                          String plate,
                          FuelType fuelType,
                          double emissions,
                          double fuelConsumption);
}
