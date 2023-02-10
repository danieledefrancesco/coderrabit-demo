package com.tuimm.learningpath.domain.routes;

import com.tuimm.learningpath.domain.places.Place;
import com.tuimm.learningpath.domain.vehicles.DrivingProfile;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Route {
    private final Place from;
    private final Place to;
    private final double distanceInKilometers;
    private final DrivingProfile drivingProfile;
}
