package com.tuimm.learningpath.vehicles.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class GetAllFuelTypesResponseDto {
    private Collection<FuelTypeResponseDto> fuelTypes;
}
