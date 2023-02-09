package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.vehicles.FuelType;
import com.tuimm.leaarningpath.domain.vehicles.Vehicle;
import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.PrintStream;
import java.util.Scanner;

@RequiredArgsConstructor
public class CreatePullmanCommand implements Command {
    @NonNull
    private final VehiclesService vehiclesService;
    @NonNull
    private final String model;
    private final int maxPeople;
    private final double dailyRentPrice;
    private final double averageSpeed;
    private final double autonomy;
    private final int stopTimeInSeconds;
    @NonNull
    private final String plate;
    @NonNull
    private final FuelType fuelType;
    private final double emissions;
    private final double fuelConsumption;

    @Override
    public void execute(PrintStream outputStream, Scanner scanner) {
        try {
            Vehicle vehicle = vehiclesService.addPullman(model,
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
            outputStream.printf("Error while creating the pullman: %s%n", e.getMessage());
        }
    }
}
