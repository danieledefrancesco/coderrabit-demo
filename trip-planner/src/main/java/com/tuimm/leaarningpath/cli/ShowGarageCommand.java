package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.io.PrintStream;

@RequiredArgsConstructor
public class ShowGarageCommand implements Command {
    @NonNull
    private final VehiclesService vehiclesService;

    @Override
    public void execute(PrintStream outputStream, InputStream inputStream) {
        outputStream.println("The following vehicles are present in the garage:");
        outputStream.println(vehiclesService.getAllVehicles());
    }
}
