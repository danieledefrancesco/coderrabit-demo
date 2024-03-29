package com.tuimm.learningpath.drivers.dal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "driving_licenses")
public class DrivingLicenseEntity {
    @Id
    @Column(name = "code")
    private String code;
    private LocalDate expiryDate;
}
