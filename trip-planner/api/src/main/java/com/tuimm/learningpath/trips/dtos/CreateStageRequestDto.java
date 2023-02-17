package com.tuimm.learningpath.trips.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStageRequestDto {
    private String from;
    private String to;
    private PreferredPlanPolicy preferredPlanPolicy;
}
