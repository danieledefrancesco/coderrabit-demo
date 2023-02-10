package com.tuimm.learningpath.domain.routes;

import com.tuimm.learningpath.domain.places.Place;
import com.tuimm.learningpath.domain.vehicles.DrivingProfile;

public interface RoutesService {
    Route getRoute(Place from, Place to, DrivingProfile drivingProfile);
}
