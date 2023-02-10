package com.tuimm.learningpath.contracts.vehicles;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class CreateVehicleResponse {
    @NonNull
    private final UUID id;
}
