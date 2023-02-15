package com.tuimm.learningpath.drivers;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DrivingLicense {
    @EqualsAndHashCode.Include
    private final DrivingLicenseCode code;
    private final LocalDate expiryDate;
}
