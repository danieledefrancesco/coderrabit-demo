package com.tuimm.learningpath.domain.drivers;

import java.util.Collection;
import java.util.UUID;

public interface DriversRepository {
    Collection<Driver> getByMinimumAge(int minimumAge);
    Driver getById(UUID id);
}
