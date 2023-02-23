package com.tuimm.learningpath.authorization;

public class UnrecognizedRoleException extends RuntimeException {
    public UnrecognizedRoleException(String message) {
        super(message);
    }
}
