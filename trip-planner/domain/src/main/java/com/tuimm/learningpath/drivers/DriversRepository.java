package com.tuimm.learningpath.drivers;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

public interface DriversRepository {
    Collection<Driver> findAll();
    Collection<Driver> findByMinimumAgeAndValidLicense(int minimumAge, LocalDate tripEndDate);
    Driver findById(UUID id);
}
