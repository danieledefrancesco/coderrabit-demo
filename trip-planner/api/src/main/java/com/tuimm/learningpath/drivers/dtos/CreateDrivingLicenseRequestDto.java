package com.tuimm.learningpath.drivers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateDrivingLicenseRequestDto {
    private String code;
    @JsonProperty("expiry_date")
    private LocalDate expiryDate;
}
