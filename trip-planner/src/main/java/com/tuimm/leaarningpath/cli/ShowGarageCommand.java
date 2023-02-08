package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;

import java.io.InputStream;
import java.io.PrintStream;

public class ShowGarageCommand implements Command {
    private final VehiclesService vehiclesService;

    public ShowGarageCommand(VehiclesService vehiclesService) {
        this.vehiclesService = vehiclesService;
    }

    @Override
    public void execute(PrintStream outputStream, InputStream inputStream) {
        outputStream.println("The following vehicles are present in the garage:");
        outputStream.println(vehiclesService.getAllVehicles());
    }
}
