package com.tuimm.learningpath.domain.trips;

import com.tuimm.learningpath.common.validators.CollectionValidator;
import com.tuimm.learningpath.common.validators.NumberValidator;
import com.tuimm.learningpath.common.validators.ObjectValidator;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TripDefinition {
    public static TripDefinition create(LocalDateTime start, List<StageDefinition> stages, int numberOfPeople) {
        return new TripDefinition(start, stages, numberOfPeople);
    }

    private final LocalDateTime start;
    private final List<StageDefinition> stages;
    private final int numberOfPeople;

    private TripDefinition(LocalDateTime start, List<StageDefinition> stages, int numberOfPeople) {
        this.start = ObjectValidator.create("start", start).ensureNotNull().getValue();
        this.stages = CollectionValidator.create("stages", stages).ensureNotNull().ensureNotEmpty().ensureAllNotNull().getValue();
        this.numberOfPeople = NumberValidator.create("numberOfPeople", numberOfPeople).ensureGreaterThen(0).getValue();
    }
}
