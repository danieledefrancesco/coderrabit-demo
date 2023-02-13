package com.tuimm.learningpath.domain.drivers;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor(staticName = "create")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DrivingLicense {
    @EqualsAndHashCode.Include
    private final DrivingLicenseCode code;
    private final LocalDate expiryDate;
}
