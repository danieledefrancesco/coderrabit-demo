package com.tuimm.learningpath.vehicles.queries;

import com.tuimm.learningpath.vehicles.FuelType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor(staticName = "create")
@Getter
public class GetAllFuelTypesResponse {
    private final Collection<FuelType> fuelTypes;

}
