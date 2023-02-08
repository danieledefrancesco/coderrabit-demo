package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.StringTokenizer;

@RequiredArgsConstructor
public class CreateBikeCommandFactory implements CommandFactory {
    @NonNull
    private final VehiclesService vehiclesService;

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
