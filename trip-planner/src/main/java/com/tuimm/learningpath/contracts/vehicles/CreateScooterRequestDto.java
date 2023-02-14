package com.tuimm.learningpath.contracts.vehicles;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateScooterRequestDto extends CreateEnginePoweredVehicleRequestDto {
    @NotNull
    @Pattern(regexp = "^[A-Z]{2}[0-9]{4}$")
    private String plate;
}
