package com.tuimm.leaarningpath.domain.routes;

import com.tuimm.leaarningpath.domain.places.Place;
import com.tuimm.leaarningpath.domain.vehicles.DrivingProfile;
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
