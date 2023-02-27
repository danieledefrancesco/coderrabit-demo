package com.tuimm.learningpath.trips.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateStageRequestDto {
    @NotNull
    @NotBlank
    private String from;
    @NotNull
    @NotBlank
    private String to;
    @NotNull
    @JsonProperty("preferred_plan_policy")
    private PreferredPlanPolicy preferredPlanPolicy;
    @NotNull
    @JsonProperty("driver_id")
    private UUID driverId;
}
