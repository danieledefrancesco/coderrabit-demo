package com.tuimm.learningpath.domain.vehicles;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
public abstract class Plate {
    private final String value;

    protected Plate(String value)
    {
        if (!Pattern.matches(getPlateRegex(), value))
        {
            throw new IllegalArgumentException("the value is not valid");
        }
        this.value = value;
    }
    protected abstract String getPlateRegex();

    @Override
    public String toString() {
        return value;
    }
}
