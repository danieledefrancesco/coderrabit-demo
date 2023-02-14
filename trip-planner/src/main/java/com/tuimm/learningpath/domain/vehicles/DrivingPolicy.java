package com.tuimm.learningpath.domain.vehicles;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class DrivingPolicy {
    private final DrivingProfile drivingProfile;
    private final int minimumDrivingAge;
    private final boolean suitableForBadWeather;
}
