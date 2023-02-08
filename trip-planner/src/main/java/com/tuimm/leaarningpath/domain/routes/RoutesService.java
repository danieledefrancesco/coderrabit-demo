package com.tuimm.leaarningpath.domain.routes;

import com.tuimm.leaarningpath.domain.places.Place;
import com.tuimm.leaarningpath.domain.vehicles.DrivingProfile;

public interface RoutesService {
    Route getRoute(Place from, Place to, DrivingProfile drivingProfile);
}
