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

    protected TripDefinition(TripDefinition.TripDefinitionBuilder<?,?> builder) {
        this.start = ObjectValidator.create("start", builder.start).ensureNotNull().getValue();
        this.stages = CollectionValidator.create("stages", builder.stages).ensureNotNull().ensureNotEmpty().ensureAllNotNull().getValue();
        this.numberOfPeople = NumberValidator.create("numberOfPeople", builder.numberOfPeople).ensureGreaterThen(0).getValue();
    }
}
