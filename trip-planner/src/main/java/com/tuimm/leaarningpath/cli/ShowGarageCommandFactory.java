package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShowGarageCommandFactory implements CommandFactory {
    @NonNull
    private final VehiclesService vehiclesService;

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
