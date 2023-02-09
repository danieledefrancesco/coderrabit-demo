package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.PrintStream;
import java.util.Scanner;

@RequiredArgsConstructor
public class ShowGarageCommand implements Command {
    @NonNull
    private final VehiclesService vehiclesService;

    @Override
    public void execute(PrintStream outputStream, Scanner scanner) {
        outputStream.println("The following vehicles are present in the garage:");
        outputStream.println(vehiclesService.getAllVehicles());
    }
}
