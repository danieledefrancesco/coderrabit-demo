package com.tuimm.learningpath.vehicles.queries;

import com.tuimm.learningpath.vehicles.Vehicle;
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
