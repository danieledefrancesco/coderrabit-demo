package com.tuimm.learningpath.vehicles.queries;

import com.tuimm.learningpath.mediator.Request;
import com.tuimm.learningpath.vehicles.Vehicle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(staticName = "fromId")
public class GetVehicleByIdRequest implements Request<Vehicle> {
    private final UUID id;
}
