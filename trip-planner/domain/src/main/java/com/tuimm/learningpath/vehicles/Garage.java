package com.tuimm.learningpath.vehicles;

import java.util.Collection;
import java.util.UUID;

public interface Garage {
    Collection<Vehicle> getAllVehicles();

    Collection<Vehicle> getSuitableVehicles(int numberOfPeople);

    void addVehicle(Vehicle vehicle);

    void delete(UUID id);
}
