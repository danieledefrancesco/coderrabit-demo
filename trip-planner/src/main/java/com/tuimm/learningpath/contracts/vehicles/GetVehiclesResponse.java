package com.tuimm.learningpath.contracts.vehicles;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class GetVehiclesResponse {
    @NonNull
    private Collection<VehicleResponse> vehicles;
}
