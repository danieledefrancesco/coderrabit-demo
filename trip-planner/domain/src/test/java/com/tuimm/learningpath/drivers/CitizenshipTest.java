package com.tuimm.learningpath.drivers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CitizenshipTest {

    @Test
    void from_shouldReturnExpectedValue_whenProvidedValueIsCorrect() {
        String value = "value";
        Assertions.assertEquals(value,
                Citizenship.from(value).getValue());
    }

    @Test
    void from_shouldThrowIllegalArgumentException_whenValueIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> Citizenship.from(null));
        Assertions.assertEquals("value cannot be null", exception.getMessage());
    }

    @Test
    void from_shouldThrowIllegalArgumentException_whenValueIsBlank() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> Citizenship.from(""));
        Assertions.assertEquals("value cannot be blank", exception.getMessage());
    }
}
