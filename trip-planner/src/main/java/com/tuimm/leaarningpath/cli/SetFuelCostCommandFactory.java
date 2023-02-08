package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.vehicles.FuelType;
import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.StringTokenizer;

@RequiredArgsConstructor
public class SetFuelCostCommandFactory implements CommandFactory {
    @NonNull
    private final VehiclesService vehiclesService;

    @Override
    public String getCommandRegex() {
        return "^set-fuel-cost (PETROL|DIESEL|LPG) \\d+\\.\\d+$";
    }

    @Override
    public String getCommandExample() {
        return "set-fuel-cost <PETROL|DIESEL|LPG> <cost>";
    }

    @Override
    public Command createCommand(String commandLine) {
        StringTokenizer stringTokenizer = new StringTokenizer(commandLine, " ");
        stringTokenizer.nextToken();
        return new SetFuelCostCommand(vehiclesService,
                FuelType.valueOf(stringTokenizer.nextToken()),
                Double.parseDouble(stringTokenizer.nextToken()));
    }
}
