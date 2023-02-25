package com.tuimm.learningpath.exceptions;

public class TripAlreadyStartedException extends RuntimeException {
    public TripAlreadyStartedException() {
        super("Trip is already started.");
    }
}
