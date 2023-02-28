package com.tuimm.learningpath.trips.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuimm.learningpath.drivers.dtos.CreateDriverRequestDto;
import com.tuimm.learningpath.validators.AtLeastOneNotNull;
import com.tuimm.learningpath.validators.AtMostOneNotNull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AtLeastOneNotNull(fields = {"driverId", "driver"})
@AtMostOneNotNull(fields = {"driverId", "driver"})
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
    @JsonProperty("driver_id")
    private UUID driverId;
    @Valid
    private CreateDriverRequestDto driver;
}
