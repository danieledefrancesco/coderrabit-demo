package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.validators.StringValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class DrivingLicenseCode {
    public static DrivingLicenseCode from(String value) {
        return new DrivingLicenseCode(value);
    }
    private final String value;

    private DrivingLicenseCode(String value) {
        this.value = StringValidator.create("value", value)
                .ensureNotNull()
                .ensureNotBlank()
                .getValue();
    }
}
