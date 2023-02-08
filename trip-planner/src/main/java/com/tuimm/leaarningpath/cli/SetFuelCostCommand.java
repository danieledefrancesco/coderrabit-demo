package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.vehicles.FuelType;
import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.io.PrintStream;

@RequiredArgsConstructor
public class SetFuelCostCommand implements Command {
    @NonNull
    private final VehiclesService vehiclesService;
    @NonNull
    private final FuelType fuelType;
    private final double cost;

    @Override
    public void execute(PrintStream outputStream, InputStream inputStream) {
        vehiclesService.setFuelCost(fuelType, cost);
        outputStream.println("The fuel cost has been updated.");
        outputStream.println(fuelType);
    }
}
