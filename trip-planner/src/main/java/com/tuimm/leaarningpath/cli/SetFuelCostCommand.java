package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.vehicles.FuelType;
import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;

import java.io.InputStream;
import java.io.PrintStream;

public class SetFuelCostCommand implements Command {
    private final VehiclesService vehiclesService;
    private final FuelType fuelType;
    private final double cost;

    public SetFuelCostCommand(VehiclesService vehiclesService, FuelType fuelType, double cost) {
        this.vehiclesService = vehiclesService;
        this.fuelType = fuelType;
        this.cost = cost;
    }

    @Override
    public void execute(PrintStream outputStream, InputStream inputStream) {
        vehiclesService.setFuelCost(fuelType, cost);
        outputStream.println("The fuel cost has been updated.");
        outputStream.println(fuelType);
    }
}
