package com.tuimm.learningpath.vehicles;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePullmanRequestDto extends CreateEnginePoweredVehicleRequestDto {
    @NotNull
    @Pattern(regexp = "^[A-Z]{2}[0-9]{3}[A-Z]{2}$")
    private String plate;
}
