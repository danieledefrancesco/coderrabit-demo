package com.tuimm.learningpath.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String className, String id) {
        this(String.format("%s with id %s does not exist.", className, id));
    }
}
