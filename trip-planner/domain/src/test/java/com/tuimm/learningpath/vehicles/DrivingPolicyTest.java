package com.tuimm.learningpath.vehicles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DrivingPolicyTest {

    @Test
    void requiresDrivingLicense_shouldReturnTrue_whenMinimumDrivingAgeIsGreaterThanZero() {
        DrivingPolicy drivingPolicy = DrivingPolicy.builder()
                .minimumDrivingAge(1)
                .drivingProfile(DrivingProfile.CAR_PROFILE)
                .suitableForBadWeather(true)
                .build();
        Assertions.assertTrue(drivingPolicy.requiresDrivingLicense());
    }
    @Test
    void requiresDrivingLicense_shouldReturnFalse_whenMinimumDrivingAgeIsEqualToZero() {
        DrivingPolicy drivingPolicy = DrivingPolicy.builder()
                .minimumDrivingAge(0)
                .drivingProfile(DrivingProfile.CAR_PROFILE)
                .suitableForBadWeather(true)
                .build();
        Assertions.assertFalse(drivingPolicy.requiresDrivingLicense());
    }

    @Test
    void requiresDrivingLicense_shouldReturnFalse_whenMinimumDrivingAgeIsLowerThanZero() {
        DrivingPolicy drivingPolicy = DrivingPolicy.builder()
                .minimumDrivingAge(-1)
                .drivingProfile(DrivingProfile.CAR_PROFILE)
                .suitableForBadWeather(true)
                .build();
        Assertions.assertFalse(drivingPolicy.requiresDrivingLicense());
    }
}
