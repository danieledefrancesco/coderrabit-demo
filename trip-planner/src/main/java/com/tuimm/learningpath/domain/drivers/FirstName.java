package com.tuimm.learningpath.domain.drivers;

import com.tuimm.learningpath.common.validators.StringValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class FirstName {
    public static FirstName from(String value) {
        return new FirstName(value);
    }
    private final String value;

    private FirstName(String value) {
        this.value = StringValidator.create("value", value)
                .ensureNotNull()
                .ensureNotBlank()
                .getValue();
    }
}
