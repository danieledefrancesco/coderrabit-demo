package com.tuimm.learningpath.domain.drivers;

import com.tuimm.learningpath.common.validators.StringValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class DrivingLicenseCode {
    private static final String PATTERN = "";
    private final String value;

    public DrivingLicenseCode(String value) {
        this.value = StringValidator.create("value", value)
                .ensureNotNull()
                .ensureNotBlank()
                .ensureMatchesPattern(PATTERN)
                .getValue();
    }
}
