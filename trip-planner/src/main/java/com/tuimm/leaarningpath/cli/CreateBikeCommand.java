package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.vehicles.Vehicle;
import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;

import java.io.InputStream;
import java.io.PrintStream;

public class CreateBikeCommand implements Command {
    private final VehiclesService vehiclesService;
    private final String model;
    private final int maxPeople;
    private final double dailyRentPrice;
    private final double averageSpeed;
    private final double autonomy;

    public CreateBikeCommand(VehiclesService vehiclesService,
                             String model,
                             int maxPeople,
                             double dailyRentPrice,
                             double averageSpeed,
                             double autonomy) {
        this.vehiclesService = vehiclesService;
        this.model = model;
        this.maxPeople = maxPeople;
        this.dailyRentPrice = dailyRentPrice;
        this.averageSpeed = averageSpeed;
        this.autonomy = autonomy;
    }

    @Override
    public void execute(PrintStream outputStream, InputStream inputStream) {
        try {
            Vehicle vehicle = vehiclesService.addBike(model,
                    maxPeople,
                    dailyRentPrice,
                    averageSpeed,
                    autonomy);
            outputStream.println("The following has been created:");
            outputStream.println(vehicle);
        } catch (Exception e) {
            outputStream.printf("Error while creating the bike: %s%n", e.getMessage());
        }
    }
}
