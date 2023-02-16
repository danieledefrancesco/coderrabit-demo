package com.tuimm.learningpath.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException(String className, String id) {
        this(String.format("%s with id %s already exists.", className, id));
    }
}
