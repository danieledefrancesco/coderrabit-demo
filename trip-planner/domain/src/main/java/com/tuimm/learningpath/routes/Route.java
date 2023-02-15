package com.tuimm.learningpath.routes;

import com.tuimm.learningpath.places.Place;
import com.tuimm.learningpath.vehicles.DrivingProfile;
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
