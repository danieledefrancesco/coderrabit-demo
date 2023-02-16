package com.tuimm.learningpath.drivers.commands;

import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.mediator.Request;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateDriverRequest implements Request<Driver> {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private CreateDrivingLicenseRequest drivingLicense;
    private String citizenship;
}
