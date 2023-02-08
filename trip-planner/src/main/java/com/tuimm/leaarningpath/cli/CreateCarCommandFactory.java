package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.vehicles.FuelType;
import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.StringTokenizer;

@RequiredArgsConstructor
public class CreateCarCommandFactory implements CommandFactory {
    @NonNull
    private final VehiclesService vehiclesService;

    @Override
    public String getCommandRegex() {
        return "^create-car [a-zA-Z0-9_.-]+ \\d+ \\d+\\.\\d+ \\d+\\.\\d+ \\d+\\.\\d+ \\d+ [a-zA-Z0-9_.-]+ (PETROL|DIESEL|LPG) \\d+\\.\\d+ \\d+\\.\\d+$";
    }

    @Override
    public String getCommandExample() {
        return "create-car <model> <max_people> <daily_rent_price> <average_speed> <autonomy> <stop_time_in_seconds> <plate> <PETROL|DIESEL|LPG> <emissions> <fuel_consumption>";
    }

    @Override
    public Command createCommand(String commandLine) {
        StringTokenizer tokenizer = new StringTokenizer(commandLine, " ");
        tokenizer.nextToken();
        return new CreateCarCommand(vehiclesService,
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
