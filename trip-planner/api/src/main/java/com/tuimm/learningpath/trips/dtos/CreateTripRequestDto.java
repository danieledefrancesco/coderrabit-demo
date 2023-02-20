package com.tuimm.learningpath.trips.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
public class CreateTripRequestDto {
    @NotNull
    private LocalDateTime start;
    @NotNull
    @NotEmpty
    @Valid
    private Collection<CreateStageRequestDto> stages;
    @Positive
    @JsonProperty("number_of_people")
    private int numberOfPeople;
}
