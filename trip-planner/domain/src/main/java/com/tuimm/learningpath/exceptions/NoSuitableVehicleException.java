package com.tuimm.learningpath.exceptions;

public class NoSuitableVehicleException extends RuntimeException {
    public NoSuitableVehicleException() {
        super("No suitable vehicle found.");
    }
}
