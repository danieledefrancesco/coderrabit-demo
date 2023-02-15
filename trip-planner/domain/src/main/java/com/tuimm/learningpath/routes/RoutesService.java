package com.tuimm.learningpath.routes;

import com.tuimm.learningpath.places.Place;
import com.tuimm.learningpath.vehicles.DrivingProfile;

public interface RoutesService {
    Route getRoute(Place from, Place to, DrivingProfile drivingProfile);
}
