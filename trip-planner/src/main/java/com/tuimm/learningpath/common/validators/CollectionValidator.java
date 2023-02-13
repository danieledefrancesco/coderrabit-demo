package com.tuimm.learningpath.common.validators;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

public class CollectionValidator<T1, T2 extends Collection<T1>> extends Validator<T2, CollectionValidator<T1, T2>>{
    public static <T1, T2 extends Collection<T1>> CollectionValidator<T1, T2> create(String parameterName, T2 value) {
        return new CollectionValidator<>(parameterName, value);
    }
    private CollectionValidator(String parameterName, T2 value) {
        super(parameterName, value);
    }

    public CollectionValidator<T1, T2> ensureAll(Predicate<? super T1> predicate, String message) {
        if (!getValue().stream().allMatch(predicate)) {
            throw new IllegalArgumentException(message);
        }
        return this;
    }

    public CollectionValidator<T1, T2> ensureAllNotNull() {
        return ensureAll(Objects::nonNull, String.format("%s cannot have null elements", getParameterName()));
    }

    public CollectionValidator<T1, T2> ensureNotEmpty() {
        if (getValue().isEmpty()) {
            throw new IllegalArgumentException(String.format("%s cannot be empty", getParameterName()));
        }
        return this;
    }
}
