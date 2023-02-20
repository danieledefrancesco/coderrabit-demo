package com.tuimm.learningpath.drivers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.mediator.Request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateDriverRequestDto implements Request<Driver> {
    @NotNull
    @NotBlank
    @JsonProperty("first_name")
    private String firstName;
    @NotNull
    @NotBlank
    @JsonProperty("last_name")
    private String lastName;
    @NotNull
    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;
    @JsonProperty("driving_license")
    private CreateDrivingLicenseRequestDto drivingLicense;
    @NotNull
    @NotBlank
    private String citizenship;
}
