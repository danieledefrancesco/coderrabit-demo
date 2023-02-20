package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.mediator.Request;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "create")
public class UpdateFuelTypeCostRequest implements Request<Void> {
    private final String fuelType;
    private final double cost;
}
