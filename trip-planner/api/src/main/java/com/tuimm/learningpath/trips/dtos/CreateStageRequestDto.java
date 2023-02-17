package com.tuimm.learningpath.trips.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStageRequestDto {
    private String from;
    private String to;
    @JsonProperty("preferred_plan_policy")
    private PreferredPlanPolicy preferredPlanPolicy;
}
