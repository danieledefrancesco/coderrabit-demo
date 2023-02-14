package com.tuimm.learningpath.application.vehicles.queries;

import com.tuimm.learningpath.domain.vehicles.Vehicle;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@Getter
@RequiredArgsConstructor(staticName = "from")
public class GetVehiclesResponse {
    @NonNull
    private Collection<Vehicle> vehicles;
}
