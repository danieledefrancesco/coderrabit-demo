package com.tuimm.learningpath.exceptions;

import com.tuimm.learningpath.common.TimeSlot;
import com.tuimm.learningpath.drivers.Driver;

public class DriverNotAvailableException extends RuntimeException {
    public static DriverNotAvailableException notCurrentlyAvailable(Driver driver) {
        return new DriverNotAvailableException(String.format("Driver with id %s is not currently available",
                driver.getId()));
    }
    public DriverNotAvailableException(Driver driver, TimeSlot timeSlot) {
        super(String.format("Driver with id %s is not available for time slot %s.",
                driver.getId(),
                timeSlot));
    }
    private DriverNotAvailableException(String message) {
        super(message);
    }
}
