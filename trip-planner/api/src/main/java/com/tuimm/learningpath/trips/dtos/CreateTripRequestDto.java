package com.tuimm.learningpath.trips.dtos;

import com.tuimm.learningpath.trips.StageDefinition;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CreateTripRequestDto {
    private LocalDateTime start;
    private List<StageDefinition> stages;
    private int numberOfPeople;
}
