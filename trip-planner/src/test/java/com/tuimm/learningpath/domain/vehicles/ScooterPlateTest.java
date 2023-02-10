package com.tuimm.learningpath.domain.vehicles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScooterPlateTest {
    @Test
    void getter_shouldReturnExpectedValue_WhenValueIsValid() {
        String plateValue = "AA0000";
        ScooterPlate genericPlate = ScooterPlate.from(plateValue);
        Assertions.assertEquals(plateValue, genericPlate.getValue());
    }

    @Test
    void from_shouldThrowIllegalArgumentException_WhenValueIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ScooterPlate.from("AA000BB"));
    }

    @Test
    void from_shouldThrowNullPointerException_WhenValueIsNull()
    {
        Assertions.assertThrows(NullPointerException.class,
                () -> ScooterPlate.from(null));
    }
}
