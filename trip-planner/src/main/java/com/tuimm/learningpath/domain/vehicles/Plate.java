package com.tuimm.learningpath.domain.vehicles;

import com.tuimm.learningpath.common.validators.StringValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public abstract class Plate {
    private final String value;

    protected Plate(String value)
    {
        this.value = StringValidator.create("value", value)
                .ensureNotNull()
                .ensureMatchesPattern(getPlateRegex())
                .getValue();
    }
    protected abstract String getPlateRegex();

    @Override
    public String toString() {
        return value;
    }
}
