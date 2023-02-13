package com.tuimm.learningpath.domain.drivers;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Driver {
    @EqualsAndHashCode.Include
    @NonNull
    private final UUID id;
    @NonNull
    private final FirstName firstName;
    @NonNull
    private final LastName lastName;
    @NonNull
    private final LocalDate dateOfBirth;
    @NonNull
    private final DrivingLicense drivingLicense;
    @NonNull
    private final Citizenship citizenship;

}
