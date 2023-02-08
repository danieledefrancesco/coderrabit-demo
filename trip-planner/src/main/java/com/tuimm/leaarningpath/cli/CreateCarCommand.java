package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.vehicles.FuelType;
import com.tuimm.leaarningpath.domain.vehicles.Vehicle;
import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;

import java.io.InputStream;
import java.io.PrintStream;

public class CreateCarCommand implements Command {
    private final VehiclesService vehiclesService;
    private final String model;
    private final int maxPeople;
    private final double dailyRentPrice;
    private final double averageSpeed;
    private final double autonomy;
    private final int stopTimeInSeconds;
    private final String plate;
    private final FuelType fuelType;
    private final double emissions;
    private final double fuelConsumption;

    public CreateCarCommand(VehiclesService vehiclesService,
                            String model,
                            int maxPeople,
                            double dailyRentPrice,
                            double averageSpeed,
                            double autonomy,
                            int stopTimeInSeconds,
                            String plate,
                            FuelType fuelType,
                            double emissions,
                            double fuelConsumption) {
        this.vehiclesService = vehiclesService;
        this.model = model;
        this.maxPeople = maxPeople;
        this.dailyRentPrice = dailyRentPrice;
        this.averageSpeed = averageSpeed;
        this.autonomy = autonomy;
        this.stopTimeInSeconds = stopTimeInSeconds;
        this.plate = plate;
        this.fuelType = fuelType;
        this.emissions = emissions;
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public void execute(PrintStream outputStream, InputStream inputStream) {
        try {
            Vehicle vehicle = vehiclesService.addCar(model,
                    maxPeople,
                    dailyRentPrice,
                    averageSpeed,
                    autonomy,
                    stopTimeInSeconds,
                    plate,
                    fuelType,
                    emissions,
                    fuelConsumption);
            outputStream.println("The following has been created:");
            outputStream.println(vehicle);
        } catch (Exception e) {
            outputStream.printf("Error while creating the car: %s%n", e.getMessage());
        }
    }
}
