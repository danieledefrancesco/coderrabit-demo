package com.tuimm.learningpath.common.validators;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class Validator<T1, T2 extends Validator<T1, ? super T2>> {
    private final String parameterName;
    private final T1 value;

    public T2 ensureNotNull() {
        if (value == null) {
            throw new IllegalArgumentException(String.format("%s cannot be null", parameterName));
        }
        return (T2) this;
    }
}
