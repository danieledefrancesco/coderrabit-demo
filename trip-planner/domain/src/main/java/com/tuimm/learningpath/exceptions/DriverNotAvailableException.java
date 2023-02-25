package com.tuimm.learningpath.exceptions;

import com.tuimm.learningpath.common.TimeSlot;
import com.tuimm.learningpath.drivers.Driver;

public class DriverNotAvailableException extends RuntimeException {
    public DriverNotAvailableException(Driver driver, TimeSlot timeSlot) {
        super(String.format("Driver with id %s is not available for time slot %s.",
                driver.getId(),
                timeSlot));
    }
}
