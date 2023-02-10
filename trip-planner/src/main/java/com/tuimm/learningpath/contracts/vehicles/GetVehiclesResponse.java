package com.tuimm.learningpath.contracts.vehicles;

import com.tuimm.learningpath.domain.vehicles.Vehicle;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
@Getter
public class GetVehiclesResponse {
    @NonNull
    private final Collection<Vehicle> vehicles;
}
