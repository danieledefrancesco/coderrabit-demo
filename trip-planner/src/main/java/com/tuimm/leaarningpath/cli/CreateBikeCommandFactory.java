package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;

import java.util.StringTokenizer;

public class CreateBikeCommandFactory implements CommandFactory {
    private final VehiclesService vehiclesService;

    public CreateBikeCommandFactory(VehiclesService vehiclesService) {
        this.vehiclesService = vehiclesService;
    }

    @Override
    public String getCommandRegex() {
        return "^create-bike [a-zA-Z0-9_.-]+ \\d+ \\d+\\.\\d+ \\d+\\.\\d+ \\d+\\.\\d+$";
    }

    @Override
    public String getCommandExample() {
        return "create-bike <model> <max_people> <daily_rent_price> <average_speed> <autonomy>";
    }

    @Override
    public Command createCommand(String commandLine) {
        StringTokenizer tokenizer = new StringTokenizer(commandLine, " ");
        tokenizer.nextToken();
        return new CreateBikeCommand(vehiclesService,
                tokenizer.nextToken(),
                Integer.parseInt(tokenizer.nextToken()),
                Double.parseDouble(tokenizer.nextToken()),
                Double.parseDouble(tokenizer.nextToken()),
                Double.parseDouble(tokenizer.nextToken()));
    }
}
