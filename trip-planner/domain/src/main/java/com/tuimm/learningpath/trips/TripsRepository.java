package com.tuimm.learningpath.trips;

import java.util.Collection;
import java.util.UUID;

public interface TripsRepository {
    Collection<Trip> findAll();
    Trip findById(UUID id);

}
