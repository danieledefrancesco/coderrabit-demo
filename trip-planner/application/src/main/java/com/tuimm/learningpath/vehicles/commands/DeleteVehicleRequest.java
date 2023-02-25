package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.mediator.Request;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(staticName = "fromId")
public class DeleteVehicleRequest implements Request<Void> {
    private final UUID vehicleId;
}
