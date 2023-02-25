package com.tuimm.learningpath.trips.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateStagePlanDriverRequestDto {
    @NotNull
    @JsonProperty("driver_id")
    private UUID driverId;
}
