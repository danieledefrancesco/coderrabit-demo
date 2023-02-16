package com.tuimm.learningpath.drivers;

import java.util.Collection;
import java.util.UUID;

public interface DriversRepository {
    Collection<Driver> findAll();
    Collection<Driver> findByMinimumAge(int minimumAge);
    Driver findById(UUID id);
    void add(Driver driver);
    void delete(UUID id);
}
