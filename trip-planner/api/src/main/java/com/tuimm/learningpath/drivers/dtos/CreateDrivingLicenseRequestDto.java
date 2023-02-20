package com.tuimm.learningpath.drivers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateDrivingLicenseRequestDto {
    @NotNull
    @NotBlank
    private String code;
    @NotNull
    @JsonProperty("expiry_date")
    private LocalDate expiryDate;
}
