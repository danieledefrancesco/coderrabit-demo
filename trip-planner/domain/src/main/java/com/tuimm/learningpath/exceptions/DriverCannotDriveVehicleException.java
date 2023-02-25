package com.tuimm.learningpath.exceptions;

import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.vehicles.Vehicle;

public class DriverCannotDriveVehicleException extends RuntimeException {
    public DriverCannotDriveVehicleException(Driver driver, Vehicle vehicle) {
        super(String.format("Driver with id %s cannot drive vehicle with id %s.",
                driver.getId(),
                vehicle.getId()));
    }
}
