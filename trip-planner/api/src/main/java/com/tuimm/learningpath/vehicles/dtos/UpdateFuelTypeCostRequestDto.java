package com.tuimm.learningpath.vehicles.dtos;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFuelTypeCostRequestDto {
    @Positive
    private double cost;
}
