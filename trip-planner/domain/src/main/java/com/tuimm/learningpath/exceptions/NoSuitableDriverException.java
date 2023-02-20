package com.tuimm.learningpath.exceptions;

public class NoSuitableDriverException extends RuntimeException {
    public NoSuitableDriverException() {
        super("No suitable driver found.");
    }
}
