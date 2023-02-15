package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.validators.StringValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Citizenship {
    public static Citizenship from(String value) {
        return new Citizenship(value);
    }
    private final String value;

    private Citizenship(String value) {
        this.value = StringValidator.create("value", value)
                .ensureNotNull()
                .ensureNotBlank()
                .getValue();
    }
}
