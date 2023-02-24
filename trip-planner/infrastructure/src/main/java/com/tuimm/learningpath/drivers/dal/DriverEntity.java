package com.tuimm.learningpath.drivers.dal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "drivers")
public class DriverEntity {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "driving_license_code", referencedColumnName = "code")
    private DrivingLicenseEntity drivingLicense;
    private String citizenship;
    @ElementCollection(targetClass = SlotEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "slot_id", referencedColumnName = "id")
    private Set<SlotEntity> reservedSlots;
}
