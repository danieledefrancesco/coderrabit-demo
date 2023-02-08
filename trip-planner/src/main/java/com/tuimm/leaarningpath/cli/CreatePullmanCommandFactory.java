package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.vehicles.FuelType;
import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;

import java.util.StringTokenizer;

public class CreatePullmanCommandFactory implements CommandFactory {
    private final VehiclesService vehiclesService;

    public CreatePullmanCommandFactory(VehiclesService vehiclesService) {
        this.vehiclesService = vehiclesService;
    }

    @Override
    public String getCommandRegex() {
        return "^create-pullman [a-zA-Z0-9_.-]+ \\d+ \\d+\\.\\d+ \\d+\\.\\d+ \\d+\\.\\d+ \\d+ [a-zA-Z0-9_.-]+ (PETROL|DIESEL|LPG) \\d+\\.\\d+ \\d+\\.\\d+$";
    }

    @Override
    public String getCommandExample() {
        return "create-pullman <model> <max_people> <daily_rent_price> <average_speed> <autonomy> <stop_time_in_seconds> <plate> <PETROL|DIESEL|LPG> <emissions> <fuel_consumption>";
    }

    @Override
    public Command createCommand(String commandLine) {
        StringTokenizer tokenizer = new StringTokenizer(commandLine, " ");
        tokenizer.nextToken();
        return new CreatePullmanCommand(vehiclesService,
                tokenizer.nextToken(),
                Integer.parseInt(tokenizer.nextToken()),
                Double.parseDouble(tokenizer.nextToken()),
                Double.parseDouble(tokenizer.nextToken()),
                Double.parseDouble(tokenizer.nextToken()),
                Integer.parseInt(tokenizer.nextToken()),
                tokenizer.nextToken(),
                FuelType.valueOf(tokenizer.nextToken()),
                Double.parseDouble(tokenizer.nextToken()),
                Double.parseDouble(tokenizer.nextToken()));
    }
}
