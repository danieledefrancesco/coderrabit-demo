package com.tuimm.learningpath;

public class ServiceInvocationException extends RuntimeException {
    public ServiceInvocationException(String message) {
        super(message);
    }

    public ServiceInvocationException(Exception inner) {
        super(inner);
    }
}
