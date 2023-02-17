package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.validators.CollectionValidator;
import com.tuimm.learningpath.validators.NumberValidator;
import com.tuimm.learningpath.validators.ObjectValidator;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@SuperBuilder
public class TripDefinition {
    private final LocalDateTime start;
    private final List<StageDefinition> stages;
    private final int numberOfPeople;

    protected TripDefinition(LocalDateTime start, List<StageDefinition> stages, int numberOfPeople) {
        this.start = ObjectValidator.create("start", start).ensureNotNull().getValue();
        this.stages = CollectionValidator.create("stages", stages).ensureNotNull().ensureNotEmpty().ensureAllNotNull().getValue();
        this.numberOfPeople = NumberValidator.create("numberOfPeople", numberOfPeople).ensureGreaterThen(0).getValue();
    }
}
