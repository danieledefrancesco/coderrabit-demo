package com.tuimm.learningpath.trips.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
public class CreateTripRequestDto {
    private LocalDateTime start;
    private Collection<CreateStageRequestDto> stages;
    @JsonProperty("number_of_people")
    private int numberOfPeople;
}
