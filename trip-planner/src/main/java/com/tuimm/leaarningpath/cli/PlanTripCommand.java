package com.tuimm.leaarningpath.cli;

import com.tuimm.leaarningpath.domain.trips.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
public class PlanTripCommand implements Command {
    @NonNull
    private final TripPlansService tripPlansService;
    @NonNull
    private final Map<String, Comparator<StagePlan>> comparators;
    @NonNull
    private final LocalDateTime tripStartDateTime;
    private final int numberOfPeople;
    private final int numberOfStages;



    @Override
    public void execute(PrintStream outputStream, Scanner scanner) {
        List<StageDefinition> stageDefinitions = new ArrayList<>(numberOfStages);
        for (int i = 0; i < numberOfStages; i++) {
            outputStream.print("Insert stage (<from> <to> <CHEAPEST|FASTEST|LEAST_POLLUTING>:");
            StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine(), " ");
            stageDefinitions.add(StageDefinition.create(tokenizer.nextToken(),
                    tokenizer.nextToken(),
                    comparators.get(tokenizer.nextToken())));
        }
        TripDefinition tripDefinition = TripDefinition.create(tripStartDateTime, stageDefinitions, numberOfPeople);
        TripPlan tripPlan = tripPlansService.planTrip(tripDefinition);
        outputStream.println(tripPlan);
    }
}
