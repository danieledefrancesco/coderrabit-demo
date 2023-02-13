package com.tuimm.learningpath.domain.drivers;

import com.tuimm.learningpath.common.validators.StringValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class LastName {
    public static LastName from(String value) {
        return new LastName(value);
    }
    private final String value;

    private LastName(String value) {
        this.value = StringValidator.create("value", value)
                .ensureNotNull()
                .ensureNotBlank()
                .getValue();
    }
}
