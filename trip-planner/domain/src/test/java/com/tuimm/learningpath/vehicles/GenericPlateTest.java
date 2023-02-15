package com.tuimm.learningpath.vehicles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GenericPlateTest {
    @Test
    void getter_shouldReturnExpectedValue_WhenValueIsValid() {
        String plateValue = "AA000BB";
        GenericPlate genericPlate = GenericPlate.from(plateValue);
        Assertions.assertEquals(plateValue, genericPlate.getValue());
    }

    @Test
    void from_shouldThrowIllegalArgumentException_WhenValueIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> GenericPlate.from("AA0000"));
    }

    @Test
    void from_shouldThrowNullPointerException_WhenValueIsNull()
    {
        Assertions.assertThrows(NullPointerException.class,
                () -> GenericPlate.from(null));
    }
}
