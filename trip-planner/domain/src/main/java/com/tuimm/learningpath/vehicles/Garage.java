package com.tuimm.learningpath.vehicles;

import java.util.Collection;
import java.util.UUID;

public interface Garage {
    Vehicle findById(UUID id);
    Collection<Vehicle> getAllVehicles();

    Collection<Vehicle> getSuitableVehicles(int numberOfPeople);
}
