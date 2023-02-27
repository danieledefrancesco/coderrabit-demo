package com.tuimm.learningpath.drivers.commands;

import com.tuimm.learningpath.drivers.Citizenship;
import com.tuimm.learningpath.drivers.FirstName;
import com.tuimm.learningpath.drivers.LastName;
import com.tuimm.learningpath.mediator.Request;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Builder
@Getter
public class UpdateDriverRequest implements Request<Void> {
    private final UUID driverId;
    private final Optional<FirstName> firstName;
    private final Optional<LastName> lastName;
    private final Optional<LocalDate> dateOfBirth;
    private final Optional<Citizenship> citizenship;
    private final Optional<CreateDrivingLicenseRequest> drivingLicense;
}
