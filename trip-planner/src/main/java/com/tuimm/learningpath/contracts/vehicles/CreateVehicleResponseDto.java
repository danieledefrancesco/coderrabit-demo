package com.tuimm.learningpath.contracts.vehicles;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateVehicleResponseDto {
    @NonNull
    private UUID id;
}
