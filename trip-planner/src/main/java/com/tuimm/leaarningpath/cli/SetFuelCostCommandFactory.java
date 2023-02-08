package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.vehicles.FuelType;
import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;

import java.util.StringTokenizer;

public class SetFuelCostCommandFactory implements CommandFactory {
    private final VehiclesService vehiclesService;

    public SetFuelCostCommandFactory(VehiclesService vehiclesService) {
        this.vehiclesService = vehiclesService;
    }

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
