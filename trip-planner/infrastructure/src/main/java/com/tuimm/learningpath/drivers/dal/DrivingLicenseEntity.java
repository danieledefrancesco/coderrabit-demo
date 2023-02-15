package com.tuimm.learningpath.drivers.dal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class DrivingLicenseEntity {
    @Id
    @Column(name = "code")
    private String code;
    private LocalDate expiryDate;
}
