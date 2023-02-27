package com.tuimm.learningpath.drivers;

import java.util.Collection;
import java.util.UUID;

public interface DriversRepository {
    Collection<Driver> findAll();
    Driver findById(UUID id);
}
