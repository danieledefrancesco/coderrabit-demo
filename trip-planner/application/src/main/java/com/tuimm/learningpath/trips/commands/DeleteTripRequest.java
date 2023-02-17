package com.tuimm.learningpath.trips.commands;

import com.tuimm.learningpath.mediator.Request;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor(staticName = "create")
@Getter
public class DeleteTripRequest implements Request<Void> {
    private final UUID tripId;
}
