package com.tuimm.learningpath.vehicles;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class GetVehiclesResponseDto {
    @NonNull
    private Collection<VehicleResponseDto> vehicles;
}
