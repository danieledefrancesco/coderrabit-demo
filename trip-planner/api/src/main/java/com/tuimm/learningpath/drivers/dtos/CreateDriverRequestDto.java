package com.tuimm.learningpath.drivers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.mediator.Request;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateDriverRequestDto implements Request<Driver> {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;
    @JsonProperty("driving_license")
    private CreateDrivingLicenseRequestDto drivingLicense;
    private String citizenship;
}
