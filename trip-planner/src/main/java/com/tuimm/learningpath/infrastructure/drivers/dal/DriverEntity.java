package com.tuimm.learningpath.infrastructure.drivers.dal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
public class DriverEntity {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driving_license_code", referencedColumnName = "code")
    private DrivingLicenseEntity drivingLicense;
    private String citizenship;
}
