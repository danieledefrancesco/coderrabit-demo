package com.tuimm.learningpath.common.validators;

public class NumberValidator<T extends Number & Comparable<T>> extends Validator<T, NumberValidator<T>> {
    public static <T extends Number & Comparable<T>> NumberValidator<T> create(String parameterName, T value) {
        return new NumberValidator<>(parameterName, value);
    }
    private NumberValidator(String parameterName, T value) {
        super(parameterName, value);
    }

    public NumberValidator<T> ensureGreaterThen(T other) {
        if (getValue().compareTo(other) <= 0) {
            throw new IllegalArgumentException(String.format("%s must be greater than %s",
                    getParameterName(),
                    other));
        }
        return this;
    }

    public NumberValidator<T> ensureGreaterThenOrEqualTo(T other) {
        if (getValue().compareTo(other) < 0) {
            throw new IllegalArgumentException(String.format("%s must be greater than or equal to %s",
                    getParameterName(),
                    other));
        }
        return this;
    }
}
