package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.trips.StagePlan;
import com.tuimm.leaarningpath.domain.trips.TripPlansService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.StringTokenizer;

@RequiredArgsConstructor
public class PlanTripCommandFactory implements CommandFactory {
    @NonNull
    private final TripPlansService tripPlansService;
    @NonNull
    private final Map<String, Comparator<StagePlan>> comparators;


    @Override
    public String getCommandRegex() {
        return "^plan-trip \\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2} \\d+ \\d+$";
    }

    @Override
    public String getCommandExample() {
        return "plan-trip <start-date-time|yyyy-MM-ddThh:mm:ss> <numberOfPeople> <numberOfStages>";
    }

    @Override
    public Command createCommand(String commandLine) {
        StringTokenizer tokenizer = new StringTokenizer(commandLine, " ");
        tokenizer.nextToken();
        return new PlanTripCommand(tripPlansService,
                comparators,
                LocalDateTime.parse(tokenizer.nextToken()),
                Integer.parseInt(tokenizer.nextToken()),
                Integer.parseInt(tokenizer.nextToken()));
    }
}
