package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;

public class ShowGarageCommandFactory implements CommandFactory {
    private final VehiclesService vehiclesService;

    public ShowGarageCommandFactory(VehiclesService vehiclesService) {
        this.vehiclesService = vehiclesService;
    }

    @Override
    public String getCommandRegex() {
        return "^show-garage$";
    }

    @Override
    public String getCommandExample() {
        return "show-garage";
    }

    @Override
    public Command createCommand(String commandLine) {
        return new ShowGarageCommand(vehiclesService);
    }
}
