package com.tuimm.learningpath.contracts.vehicles;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
@Getter
public class GetVehiclesResponse {
    @NonNull
    private final Collection<VehicleResponse> vehicles;
}
