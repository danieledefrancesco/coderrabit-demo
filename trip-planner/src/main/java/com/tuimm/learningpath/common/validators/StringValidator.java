package com.tuimm.learningpath.common.validators;

import java.util.regex.Pattern;


public class StringValidator extends Validator<String, StringValidator>{
    public static StringValidator create(String parameterName, String value) {
        return new StringValidator(parameterName, value);
    }
    private StringValidator(String parameterName, String value) {
        super(parameterName, value);
    }

    public StringValidator ensureNotBlank() {
        if (getValue().isBlank()) {
            throw new IllegalArgumentException(String.format("%s cannot be blank", getParameterName()));
        }
        return this;
    }

    public StringValidator ensureMatchesPattern(String pattern) {
        if (!Pattern.matches(pattern, getValue())) {
            throw new IllegalArgumentException(String.format("%s doesnt match pattern %s", getParameterName(), pattern));
        }
        return this;
    }
}
