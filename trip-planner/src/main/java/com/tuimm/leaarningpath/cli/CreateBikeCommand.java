package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.vehicles.Vehicle;
import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.PrintStream;
import java.util.Scanner;

@RequiredArgsConstructor
public class CreateBikeCommand implements Command {
    @NonNull
    private final VehiclesService vehiclesService;
    @NonNull
    private final String model;
    private final int maxPeople;
    private final double dailyRentPrice;
    private final double averageSpeed;
    private final double autonomy;

    @Override
    public void execute(PrintStream outputStream, Scanner scanner) {
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
