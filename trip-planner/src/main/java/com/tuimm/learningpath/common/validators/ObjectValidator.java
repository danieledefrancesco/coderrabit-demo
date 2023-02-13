package com.tuimm.learningpath.common.validators;

public class ObjectValidator<T> extends Validator<T, ObjectValidator<T>> {
    public static <T> ObjectValidator<T> create(String parameterName, T value) {
        return new ObjectValidator<>(parameterName, value);
    }
    private ObjectValidator(String parameterName, T value) {
        super(parameterName, value);
    }
}
